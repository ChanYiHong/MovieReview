package HCY.MovieReview.service;

import HCY.MovieReview.dto.MovieDTO;
import HCY.MovieReview.dto.PageRequestDTO;
import HCY.MovieReview.dto.PageResponseDTO;
import HCY.MovieReview.entity.Movie;
import HCY.MovieReview.entity.MovieImage;
import HCY.MovieReview.repository.MovieImageRepository;
import HCY.MovieReview.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    private final MovieImageRepository movieImageRepository;

    @Transactional
    @Override
    public Long register(MovieDTO dto) {
        Map<String, Object> entityMap = dtoToEntity(dto);
        Movie movie = (Movie) entityMap.get("movie");
        List<MovieImage> movieImages = (List<MovieImage>) entityMap.get("imgList");

        movieRepository.save(movie);
        log.info("movie " + movie);
        log.info("movieImages " + movieImages);

        movieImages.forEach(movieImage -> {
            movieImageRepository.save(movieImage);
        });

        return movie.getId();
    }

    @Override
    public PageResponseDTO<Object[], MovieDTO> list(PageRequestDTO pageRequestDTO) {
        Page<Object[]> result = movieRepository.getListPage(pageRequestDTO.getPageable(Sort.by("id").descending()));
        Function<Object[], MovieDTO> fn = arr -> entitiesToDTO(
                (Movie) arr[0], (List<MovieImage>) Arrays.asList((MovieImage) arr[1]), (double) arr[2], (Long) arr[3]);

        return new PageResponseDTO<>(result, fn);
    }

    @Override
    public MovieDTO read(Long id) {
        List<Object[]> result = movieRepository.getMovieWithAll(id);

        Movie movie = (Movie) result.get(0)[0];

        List<MovieImage> movieImages = new ArrayList<>();
        result.forEach(arr -> {
            MovieImage movieImage = (MovieImage) arr[1];
            movieImages.add(movieImage);
        });

        Double avg = (Double) result.get(0)[2];
        Long reviewCnt = (Long) result.get(0)[3];

        return entitiesToDTO(movie, movieImages, avg, reviewCnt);

    }
}

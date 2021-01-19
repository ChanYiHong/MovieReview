package HCY.MovieReview.service;

import HCY.MovieReview.dto.MovieDTO;
import HCY.MovieReview.entity.Movie;
import HCY.MovieReview.entity.MovieImage;
import HCY.MovieReview.repository.MovieImageRepository;
import HCY.MovieReview.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
        movieImages.forEach(movieImage -> {
            movieImageRepository.save(movieImage);
        });
        return movie.getId();
    }
}

package HCY.MovieReview.service;

import HCY.MovieReview.dto.MovieDTO;
import HCY.MovieReview.dto.MovieImageDTO;
import HCY.MovieReview.dto.PageRequestDTO;
import HCY.MovieReview.dto.PageResponseDTO;
import HCY.MovieReview.entity.Movie;
import HCY.MovieReview.entity.MovieImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {

    Long register(MovieDTO dto);

    PageResponseDTO<Object[], MovieDTO> list(PageRequestDTO pageRequestDTO);

    MovieDTO read(Long id);

    default MovieDTO entitiesToDTO(Movie movie, List<MovieImage> movieImages, double avg, Long reviewCnt){
        MovieDTO movieDTO = MovieDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .createdDate(movie.getCreatedDate())
                .modifiedDate(movie.getModifiedDate())
                .build();

        List<MovieImageDTO> movieImageDtos = movieImages.stream()
                .map(movieImage -> {
                    MovieImageDTO imageDto = MovieImageDTO.builder()
                            .uuid(movieImage.getUuid())
                            .path(movieImage.getPath())
                            .imgName(movieImage.getImgName())
                            .build();

                    return imageDto;
                }).collect(Collectors.toList());

        movieDTO.setImageDTOList(movieImageDtos);
        movieDTO.setAvg(avg);
        movieDTO.setReviewCnt(reviewCnt.intValue());

        return movieDTO;
    }

    default Map<String, Object> dtoToEntity(MovieDTO dto) {
        Map<String, Object> entityMap = new HashMap<>();

        Movie movie = Movie.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .build();

        entityMap.put("movie", movie);

        List<MovieImageDTO> movieImageDtos = dto.getImageDTOList();

        System.out.println("요기요!!");
        System.out.println("++++++++++++++++++++");
        System.out.println(movieImageDtos);

        if(movieImageDtos != null && movieImageDtos.size() > 0) {

            List<MovieImage> movieImages = movieImageDtos.stream()
                    .map(movieImageDTO -> {
                        MovieImage movieImage = MovieImage.builder()
                                .uuid(movieImageDTO.getUuid())
                                .imgName(movieImageDTO.getImgName())
                                .path(movieImageDTO.getPath())
                                .movie(movie)
                                .build();
                        return movieImage;
                    }).collect(Collectors.toList());

            entityMap.put("imgList", movieImages);
        }

        return entityMap;
    }

}

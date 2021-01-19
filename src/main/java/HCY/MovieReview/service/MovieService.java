package HCY.MovieReview.service;

import HCY.MovieReview.dto.MovieDTO;
import HCY.MovieReview.dto.MovieImageDTO;
import HCY.MovieReview.entity.Movie;
import HCY.MovieReview.entity.MovieImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {

    Long register(MovieDTO dto);

    default Map<String, Object> dtoToEntity(MovieDTO dto) {
        Map<String, Object> entityMap = new HashMap<>();

        Movie movie = Movie.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .build();

        entityMap.put("movie", movie);

        List<MovieImageDTO> movieImageDtos = dto.getImageDTOList();

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

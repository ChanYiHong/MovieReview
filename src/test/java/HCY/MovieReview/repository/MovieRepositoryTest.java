package HCY.MovieReview.repository;

import HCY.MovieReview.entity.Movie;
import HCY.MovieReview.entity.MovieImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieRepositoryTest {

    @Autowired MovieRepository movieRepository;
    @Autowired MovieImageRepository movieImageRepository;

    @Commit
    @Transactional
    @Test
    public void insertMovies() throws Exception {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Movie movie = Movie.builder().title("Movie..." + i).build();
            movieRepository.save(movie);
            int count = (int)(Math.random() * 5) + 1; // 1,2,3,4

            for(int j = 0; j < count ; j++) {
                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("test" + j + ".jpg").build();
                movieImageRepository.save(movieImage);
            }
        });
    }

    @Test
    public void testListPage() throws Exception {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
        Page<Object[]> result = movieRepository.getListPage(pageable);
        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void testListPage2() throws Exception {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
        Page<Object[]> result = movieRepository.getListPage2(pageable);
        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void testListPage3() throws Exception {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
        Page<Object[]> result = movieRepository.getListPage3(pageable);
        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void testGetMovieWithAll() throws Exception {
        List<Object[]> results = movieRepository.getMovieWithAll(10L);
        for(Object[] result : results) {
            System.out.println(Arrays.toString(result));
        }
    }
}
package HCY.MovieReview.repository;

import HCY.MovieReview.entity.Member;
import HCY.MovieReview.entity.Movie;
import HCY.MovieReview.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewRepositoryTest {

    @Autowired ReviewRepository reviewRepository;

    @Test
    public void insertReviews() throws Exception {

        IntStream.rangeClosed(1, 200).forEach(i -> {
            Long movieId = (long)(Math.random()*100) + 1;
            Long reviewerId = (long)(Math.random()*100) + 1;
            Member member = Member.builder().id(reviewerId).build();
            Movie movie = Movie.builder().id(movieId).build();

            Review review = Review.builder()
                    .member(member)
                    .movie(movie)
                    .grade((int) (Math.random() * 5) + 1)
                    .text("Review for this movie..." + i)
                    .build();
            reviewRepository.save(review);
        });
    }

    @Transactional
    @Test
    public void isMovieAndMemberAllRight() throws Exception {
        Review review = reviewRepository.getOne(10L);
        System.out.println(review.getMember().getId());
        System.out.println(review.getMember().getEmail());
        System.out.println(review.getMember().getNickname());
        System.out.println(review.getMember().getPassword());
        System.out.println(review.getMovie().getId());
        System.out.println(review.getMovie().getTitle());
    }

    @Test
    public void testGetMovieReviews() throws Exception {
        Movie movie = Movie.builder().id(50L).build();
        List<Review> result = reviewRepository.findByMovie(movie);
        result.forEach(movieReview -> {
            System.out.println(movieReview.getId());
            System.out.println(movieReview.getGrade());
            System.out.println(movieReview.getText());
            System.out.println(movieReview.getMember().getEmail());
        });
    }

}
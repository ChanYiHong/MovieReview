package HCY.MovieReview.service;

import HCY.MovieReview.dto.ReviewDTO;
import HCY.MovieReview.entity.Movie;
import HCY.MovieReview.entity.Review;
import HCY.MovieReview.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> list(Long movieId) {
        log.info("Get Review List of Movie id : " + movieId);
        Movie movie = Movie.builder().id(movieId).build();
        List<Review> result = reviewRepository.findByMovie(movie);
        return entitiesToDTOs(result);
    }

    @Override
    @Transactional
    public Long save(ReviewDTO reviewDTO) {
        log.info("Save Review : " + reviewDTO);
        Review review = reviewRepository.save(dtoToEntity(reviewDTO));
        return review.getId();
    }

    @Override
    @Transactional
    public void modify(ReviewDTO reviewDTO) {
        log.info("Modify Review");
        Review review = reviewRepository.getOne(reviewDTO.getId());
        review.changeGrade(reviewDTO.getGrade());
        review.changeText(reviewDTO.getText());
    }

    @Override
    @Transactional
    public void remove(Long reviewId) {
        log.info("Remove Review");
        reviewRepository.deleteById(reviewId);
    }
}

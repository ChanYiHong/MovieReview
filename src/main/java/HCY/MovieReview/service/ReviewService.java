package HCY.MovieReview.service;

import HCY.MovieReview.dto.ReviewDTO;
import HCY.MovieReview.entity.Member;
import HCY.MovieReview.entity.Movie;
import HCY.MovieReview.entity.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface ReviewService {

    List<ReviewDTO> list(Long movieId);

    Long save(ReviewDTO reviewDTO);

    void modify(ReviewDTO reviewDTO);

    void remove(Long reviewId);

    default List<ReviewDTO> entitiesToDTOs(List<Review> reviews){
        List<ReviewDTO> dtoList = reviews.stream()
                .map(review -> {
                    ReviewDTO reviewDTO = ReviewDTO.builder()
                            .id(review.getId())
                            .text(review.getText())
                            .grade(review.getGrade())
                            .createdDate(review.getCreatedDate())
                            .modifiedDate(review.getModifiedDate())
                            .movieId(review.getMovie().getId())
                            .memberId(review.getMember().getId())
                            .nickName(review.getMember().getNickname())
                            .email(review.getMember().getEmail())
                            .build();

                    return reviewDTO;
                }).collect(Collectors.toList());

        return dtoList;
    }

    default Review dtoToEntity(ReviewDTO reviewDTO) {
        Movie movie = Movie.builder().id(reviewDTO.getMovieId()).build();
        Member member = Member.builder().id(reviewDTO.getMemberId()).build();
        return Review.builder()
                .id(reviewDTO.getId())
                .text(reviewDTO.getText())
                .grade(reviewDTO.getGrade())
                .movie(movie)
                .member(member)
                .build();
    }

}

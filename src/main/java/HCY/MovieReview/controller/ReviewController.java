package HCY.MovieReview.controller;

import HCY.MovieReview.dto.ReviewDTO;
import HCY.MovieReview.entity.Movie;
import HCY.MovieReview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping(value = "/{movieId}/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReviewDTO>> list(@PathVariable("movieId") Long id) {

        List<ReviewDTO> list = reviewService.list(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> save(@RequestBody ReviewDTO reviewDTO) {

        Long reviewId = reviewService.save(reviewDTO);
        return new ResponseEntity<>(reviewId, HttpStatus.OK);
    }

    @PutMapping(value = "/modify/{reviewId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> modify(@PathVariable("reviewId") Long id, @RequestBody ReviewDTO reviewDTO) {

        reviewService.modify(reviewDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping(value = "/remove/{reviewId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> delete(@PathVariable("reviewId") Long id) {

        reviewService.remove(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}

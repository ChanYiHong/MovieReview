package HCY.MovieReview.service;

import HCY.MovieReview.dto.ReviewDTO;
import HCY.MovieReview.entity.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewServiceTest {

    @Autowired ReviewService reviewService;

    @Test
    public void testList() throws Exception {

        List<ReviewDTO> list = reviewService.list(15L);

        list.forEach(reviewDTO -> {
            System.out.println(reviewDTO);
        });

    }

}
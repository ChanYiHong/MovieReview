package HCY.MovieReview.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReviewDTO {

    private Long id;
    private String text;
    private int grade;
    private LocalDateTime createdDate, modifiedDate;

    private Long movieId;
    private Long memberId;

    private String nickName;
    private String email;

}

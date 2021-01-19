package HCY.MovieReview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDTO {

    private Long id;
    private String title;

    // Builder에 기본값이 null인데, 이를 new ArrayList<>(); 로 해준 케이스.
    @Builder.Default
    private List<MovieImageDTO> imageDTOList = new ArrayList<>();

}

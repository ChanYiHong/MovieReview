package HCY.MovieReview.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Movie extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

}

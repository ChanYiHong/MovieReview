package HCY.MovieReview.entity;

import lombok.*;

import javax.persistence.*;

@ToString(exclude = {"member", "movie"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Review extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    private int grade;
    private String text;

}

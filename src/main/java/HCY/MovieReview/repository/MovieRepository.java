package HCY.MovieReview.repository;

import HCY.MovieReview.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select m, avg(coalesce(r.grade, 0)), count(distinct r) from Movie m "
            + "left join Review r on r.movie = m group by m")
    Page<Object[]> getListPage1(Pageable pageable);


    // 비효율적인 코드. max 때문에 영화 의 모든 movie image를 다 가져오는 쿼리가 나옴.. N + 1 문제.
    @Query("select m, avg(coalesce(r.grade, 0)), count(distinct r), max(mi) from Movie m "
            + "left join MovieImage mi on mi.movie = m "
            + "left join Review r on r.movie = m group by m")
    Page<Object[]> getListPage2(Pageable pageable);

    // 효율적으로 바꾼 코드. max를 제거함.
    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(distinct r) from Movie m "
            + "left join MovieImage mi on mi.movie = m "
            + "left join Review r on r.movie = m group by m")
    Page<Object[]> getListPage(Pageable pageable);

//    // Fetch Join 써볼까~ 공부 후 진행..
//    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(distinct r) from Movie m "
//            + "join fetch "
//            + "left join Review r on r.movie = m group by m")
//    Page<Object[]> getListPage4(Pageable pageable);

    // 영화 조회..
    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(distinct r) from Movie m " +
            "left join MovieImage mi on mi.movie = m " +
            "left join Review r on r.movie = m where m.id =:id group by mi")
    List<Object[]> getMovieWithAll(@Param("id") Long id);

    @Query("select m, avg(coalesce(r.grade, 0)) from Movie m left join Review r on r.movie = m group by m")
    List<Object[]> getMovieListWithAvgRate();

}

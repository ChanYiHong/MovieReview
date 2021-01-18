package HCY.MovieReview.repository;

import HCY.MovieReview.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

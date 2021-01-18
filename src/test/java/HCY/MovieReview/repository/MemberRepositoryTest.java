package HCY.MovieReview.repository;

import HCY.MovieReview.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Autowired ReviewRepository reviewRepository;

    @Test
    public void insertMembers() throws Exception {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("hcy" + i + "@naver.com")
                    .password("1111")
                    .nickname("reviewer" + i).build();
            memberRepository.save(member);
        });
    }

    @Test
    @Transactional
    @Commit
    public void testDeleteMember() throws Exception {
        Member member = Member.builder().id(10L).build();

        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(10L);
    }
}
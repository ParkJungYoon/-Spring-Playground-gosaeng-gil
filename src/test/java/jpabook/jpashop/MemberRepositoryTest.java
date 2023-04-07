package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    // Test에 Transactional 어노테이션이 있으면 테스트가 끝난 다음 db rollback을 해준다.
    // 디비에서 확인하고 싶으면 Rollback을 끌 수 있다.
    @Test
    @Transactional
//    @Rollback(false)
    public void testMember() throws Exception {
        // given
        Member member = new Member();
        member.setName("memberA");

        // when
        memberRepository.save(member);
        Long savedId = member.getId();
        Member findMember = memberRepository.findOne(savedId);

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
        Assertions.assertThat(findMember).isEqualTo(member);
        // 같은 트랜잭션 안에서, 같은 영속성 컨텍스트 안에서 저장하고 조회하면 id값이 같으면 같은 엔티티로 식별₩
        System.out.println("findMember == member" + (findMember == member));
    }
}
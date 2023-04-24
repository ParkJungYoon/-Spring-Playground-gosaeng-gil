package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** =중요=// jpa의 모든 데이터 변경 로직은 트랜잭션 안에서 실행되어야 한다.
 * 클래스 단위로 걸어도 되지만 메서드 단위로도 가능
 * 조회만 하는 경우 리드온니로 하면 리소스를 효율적으로 사용 가능
 * 현재는 읽기가 더 많아서 클래스 단위에는 읽기 전용하고 쓰기에만 따로 어노테이션을 달아주면 된다.
 */

/**
 * lombok에 @AllArgsConstructor가 생성자 주입 코드를 직접 넣어준다.
 * @RequiredArgsConstructor는 final로 된 친구들 주입
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    /**
     * 2. 필드 주입이고 private 못바꾼다. 테스트할 때는?
     * 3. setter로 주입하면 테스트할 때 mockdata 주입할 수 있다.
     * 하지만 런타임에서 애플리케이션이 실행되고 있을 때 누군가 바꿔버릴수도 있다.
     * 1. 그래서 요즘 자주 쓰는건 생성자 주입
     * 장점: 테스트 케이스에서 repository 생성할 때 명확하게 뭐에 의존하고 있는지 뭘 넣어줘야할지 알 수 있다.
     */
    private final MemberRepository memberRepository;

    // 요즘 스프링에서 생성자가 한 개면 autowired 안붙여줘도 자동으로 주입해준다.
//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //    @Autowired
//    private MemberRepository memberRepository;

//    private MemberRepository memberRepository;
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);  // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // 이것도 문제 발생할 수 있음.
    // 동시에 같은 이름으로 Insert하면 둘 다 validate를 통과해서 save될 수 있음
    // 그래서 DB에 member name을 유니크 제약조건을 추가
    private void validateDuplicateMember(Member member) {
        // EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {  // length가 1이상이면 문제가 있다라고 구현 가능
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}

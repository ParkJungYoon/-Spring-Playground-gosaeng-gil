package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;  // 주문 회원

    /** 즉시로딩(EAGER)을 사용한 경우
     * n+1 문제
     * order조회 하나 눌렀는데 결과가 100개면 member를 가져오기 위해서 단방 쿼리를 100번 날림
     * N(member) + 1(order)
     *
     * OneToMany는 fetch 전략이 Lazy인데,
     * ManyToOne은 fetch 전략이 Eager이다.
     *
     * 결론: ManyToOne, OneToOne 모두 Lazy로 바꿔줘야한다.
     */

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    // 연관관계 주인으로 둔다.
    // 1:1 관계에서는 fk를 어디에 둬도 된다. 이때 보통 엑세스가 많이 발생하는 곳에 fk를 둔다.
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;  // 배송 정보

    // LocalDateTime쓰면 하이버네이트가 자동으로 지원해준다.
    // orderDate를 order_date로 바꿔준다.
    private LocalDateTime orderDate;  // 주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status;  // 주문의 상태 [ORDER, CANCEL]
}

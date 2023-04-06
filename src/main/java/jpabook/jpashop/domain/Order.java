package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;  // 주문 회원

    private List<OrderItem> orderItems = new ArrayList<>();

    private Delivery delivery;  // 배송 정보

    // LocalDateTime쓰면 하이버네이트가 자동으로 지원해준다.
    private LocalDateTime orderDate;  // 주문 시간

    private OrderStatus status;  // 주문의 상태 [ORDER, CANCEL]
}

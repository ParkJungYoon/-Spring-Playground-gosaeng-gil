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
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;  // 주문 회원

    /**
     * 즉시로딩(EAGER)을 사용한 경우
     * n+1 문제
     * order조회 하나 눌렀는데 결과가 100개면 member를 가져오기 위해서 단방 쿼리를 100번 날림
     * N(member) + 1(order)
     * <p>
     * OneToMany는 fetch 전략이 Lazy인데,
     * ManyToOne은 fetch 전략이 Eager이다.
     * <p>
     * 결론: ManyToOne, OneToOne 모두 Lazy로 바꿔줘야한다.
     */

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    /**
     * cascade
     * 1. cascade를 안하면 엔티티를 모두 persist 해줘야 한다.
     * persist(orderItemA)
     * persist(orderItemB)
     * persist(orderItemC)
     * persist(order)
     * <p>
     * 2. cascade를 하면 한번만 하면 전파
     * persist(order)
     */

    // 연관관계 주인으로 둔다.
    // 1:1 관계에서는 fk를 어디에 둬도 된다. 이때 보통 엑세스가 많이 발생하는 곳에 fk를 둔다.
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;  // 배송 정보

    // LocalDateTime쓰면 하이버네이트가 자동으로 지원해준다.
    // orderDate를 order_date로 바꿔준다.
    private LocalDateTime orderDate;  // 주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status;  // 주문의 상태 [ORDER, CANCEL]

    //==연관관계 편의 메서드==//
    // 핵심적으로 컨트롤하는 쪽이 가지고 있는게 좋다.
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==생성 메서드==//
    // ... 은 가변인자 파라미터
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //==비즈니스 로직==//

    /**
     * 주문 취소
     */
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    //==조회 로직==//
    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice() {
        return orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }
}

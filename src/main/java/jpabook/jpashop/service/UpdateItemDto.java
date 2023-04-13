package jpabook.jpashop.service;

import lombok.Getter;
import lombok.Setter;

// 변경할 값이 많은 경우 dto 만들어서 넘겨줘도 된다.
@Getter @Setter
public class UpdateItemDto {
    private String name;
    private int price;
    private int stockQuantity;
}

package com.market.carrot.daangn.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private  int orderPrice;

    private int count;

//    protected OrderItem() {
//
//    }

    /**
     * 비즈니스 로직 구현
     */
    public static OrderItem createOrderItem (Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

//        item.removeStock(count);
        return orderItem;
    }

//    public void cancel() {
//        getItem().addStock(count);//재고를 주문수량만큼 늘려줘
//    }

    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }


}

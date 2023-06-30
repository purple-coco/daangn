package com.market.carrot.daangn.service;

//updateItem에서 넘겨주는 파라미터가 많은 경우 DTO를 이용하여 동작하도록 설계 가능

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateItemDto {

    private String itemName;
    private int price;
    private int stockQuantity;

}

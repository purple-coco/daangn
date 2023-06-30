package com.market.carrot.daangn.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;


@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    //JPA 스펙 상 설계
    protected Address() {

    }

    //생성할 때만 값이 세팅되도록 설계
    // 값 타입은 변경 불가능하게 설계
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}

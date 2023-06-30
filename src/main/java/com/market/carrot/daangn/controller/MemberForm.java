package com.market.carrot.daangn.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "아이디 입력은 필수입니다.")
    private String username;

    @NotEmpty(message = "비밀번호 입력은 필수입니다.")
    private String password;

    @NotEmpty(message = "회원 이름 입력은 필수입니다.")
    private String name;


    private String city;
    private String street;
    private String zipcode;
}

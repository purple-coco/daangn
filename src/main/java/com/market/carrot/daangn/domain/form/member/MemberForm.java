package com.market.carrot.daangn.domain.form.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "아이디 입력은 필수입니다.")
    private String username;

    @NotEmpty(message = "비밀번호 입력은 필수입니다.")
    @Pattern(message = "최소 8자 이상, 영어 대·소문자, 숫자, 특수문자가 혼용되어야 합니다."
            , regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$")
    private String password;

    @NotEmpty(message = "비밀번호 확인이 필요합니다.")
    private String password2;

    @NotEmpty(message = "회원 이름 입력은 필수입니다.")
    private String name;


    private String city;
    private String street;
}

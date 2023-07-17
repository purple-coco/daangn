package com.market.carrot.daangn.domain.form.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberLoginForm {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}

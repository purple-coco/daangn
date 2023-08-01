package com.market.carrot.daangn.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberRole {
    USER("USER"),
    ADMIN("ADMIN");

    private String value;
}

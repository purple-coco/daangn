package com.market.carrot.daangn.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;

@Entity
@Getter @Setter
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String password2;

    @NotEmpty
    private String name;

    @Enumerated(EnumType.STRING)
    private MemberRole authority;

    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    /**
     * 회원 생성 메서드
     */
    public static Member createMember(String username, String password, String password2, String name, Address address) {
        Member member = new Member();

        member.setUsername(username);
        member.setPassword(password);
        member.setPassword2(password2);
        member.setName(name);
        member.setAddress(address);

        return member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> String.valueOf(authority));
    }
}

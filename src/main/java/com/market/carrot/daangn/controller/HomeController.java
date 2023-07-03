package com.market.carrot.daangn.controller;

import com.market.carrot.daangn.domain.Member;
import com.market.carrot.daangn.domain.form.MemberLoginForm;
import com.market.carrot.daangn.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;


    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/users/login")
    public String loginForm(@ModelAttribute("loginForm") MemberLoginForm form) {
        return "members/loginForm";
    }

    @PostMapping("/users/login")
    public String LoginForm(@Validated @ModelAttribute("loginForm") MemberLoginForm form,
                            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "members/loginForm";
        }

        boolean loginMember = memberService.loginMember(form.getUsername(), form.getPassword());

        if (!loginMember) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호 확인이 필요합니다.");
            return "members/loginForm";
        } else {
            return "redirect:/";

        }


    }
}

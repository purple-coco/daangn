package com.market.carrot.daangn.controller;

import com.market.carrot.daangn.config.jwt.JwtUtil;
import com.market.carrot.daangn.domain.Member;
import com.market.carrot.daangn.domain.form.member.MemberLoginForm;
import com.market.carrot.daangn.domain.session.SessionConst;
import com.market.carrot.daangn.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Key;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    @Value("${jwt.secret}")
    private String secretKey;

    private Long expiredMs = 1000 * 60 * 60L;

    private final MemberService memberService;

    @GetMapping("/")
    public String home(Member loginMember, Model model) {

        if (loginMember == null) {
            return "home";
        }

        //modelAttribute가 아니라 우리가 만든 argumentresolver가 동작하도록 구현 필요
        model.addAttribute("member", loginMember);
        return "loginHome";

    }

    @GetMapping("/members/login")
    public String loginForm(@ModelAttribute("loginForm") MemberLoginForm form) {
        log.info("로그인폼 접근");
        return "members/loginMemberForm";
    }

    @PostMapping("/members/login")
    public String LoginForm(@Valid @ModelAttribute("loginForm") MemberLoginForm form,
                            BindingResult bindingResult,
                            HttpServletRequest request) {

        log.info("로그인폼 실행");
        if(bindingResult.hasErrors()) {
            return "members/loginMemberForm";
        }

        log.info("사용자 검증 Controller");

        Member loginMember = memberService.loginMember(form.getUsername(), form.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호 확인이 필요합니다.");
            return "members/loginMemberForm";
        }

        HttpSession session = request.getSession();;
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return JwtUtil.createJWT(form.getUsername(), secretKey, expiredMs);

//        return "loginHome";


    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}

package com.market.carrot.daangn.controller;

import com.market.carrot.daangn.domain.Address;
import com.market.carrot.daangn.domain.Member;
import com.market.carrot.daangn.domain.form.member.MemberForm;
import com.market.carrot.daangn.repository.MemberRepository;
import com.market.carrot.daangn.service.MemberService;
import com.market.carrot.daangn.validation.MemberValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;


    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());

        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@ModelAttribute("memberForm") MemberForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet());


        Member member = Member.createMember(form.getUsername(), form.getPassword(), form.getPassword2(),  form.getName(), address);

        memberService.join(member);
        return "home";
    }

}

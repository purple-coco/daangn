package com.market.carrot.daangn.validation;

import com.market.carrot.daangn.domain.Item;
import com.market.carrot.daangn.domain.Member;
import com.market.carrot.daangn.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class MemberValidator implements Validator {

    private final MemberService memberService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Member.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Member memberForm = (Member) target;

        // 비밀번호 복잡도 로직 적용
        if (memberService.passwordValidate(memberForm.getPassword())) {
            errors.rejectValue("password", "required");
        }

        // 비밀번호 , 비밀번호 확인 입력 받아서 둘이 일치하는지 확인하는 로직 구현
        if (!memberService.passwordEquals(memberForm.getPassword(), memberForm.getPassword2())) {
            errors.rejectValue("password2", "fix");

        }
    }
}

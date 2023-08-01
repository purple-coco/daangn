package com.market.carrot.daangn.service;

import com.market.carrot.daangn.domain.Member;
import com.market.carrot.daangn.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    /* 회원 가입 */
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();// 항상 값이 존재한다는 보장이 있기 때문에 id 반환


    }

    /* 비밀번호 복잡도 규칙 적용 */
    public boolean passwordValidate(String password) {
        String passwordPolicy = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$";

        Pattern pattern = Pattern.compile(passwordPolicy);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    /* 비밀번호 일치 여부 확인 */
    public boolean passwordEquals(String password1, String password2) {
        log.info("{}", password1);
        log.info("{}", password2);
        log.info("{}", password1.equals(password2));


        return password1.equals(password2);
    }


    /* 중복 회원 검증 */
    private void validateDuplicateMember(Member member) {
        Member findMembers = memberRepository.findByUsername(member.getUsername());

        if (!findMembers().isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    /* 로그인 */
    public Member loginMember(String username, String password) {

        Member loginMember = memberRepository.findByUsername(username);

        log.info("사용자 검증 Service{}", loginMember);

        if (!loginMember.getUsername().equals(username) ||
            !loginMember.getPassword().equals(password)) {

            return null;
        }

        return loginMember;
    }



    /* 회원 전체 조회 */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOneById(memberId);
    }

    public Member findByUsername(String username) { return memberRepository.findByUsername(username); }

}

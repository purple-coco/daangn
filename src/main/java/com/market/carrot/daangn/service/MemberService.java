package com.market.carrot.daangn.service;

import com.market.carrot.daangn.domain.Member;
import com.market.carrot.daangn.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    /* 회원 가입 */
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();// 항상 값이 존재한다는 보장이 있기 때문에 id 반환
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

}

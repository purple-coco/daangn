package com.market.carrot.daangn.service;

import com.market.carrot.daangn.domain.Member;
import com.market.carrot.daangn.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setUsername("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        //저장한 멤버와 가입한 멤버 비교
        //가입이 정상적으로 이루어짐
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setUsername("jo");

        Member member2 = new Member();
        member2.setUsername("jo");

        //when
        memberService.join(member1);
        memberService.join(member2);


        //then
        fail("예외가 발생한다.");
    }
}

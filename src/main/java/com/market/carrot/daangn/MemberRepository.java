//package com.market.carrot.daangn;
//
//import com.market.carrot.daangn.domain.Member;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
////DAO
////Entity를 찾아줘
//@Repository
//public class MemberRepository {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    public Long save(Member member) {
//        em.persist(member);
//        return member.getId();//커맨드와 쿼리를 분리하기 위해 멤버를 반환하는 것이 아닌 id를 반환
//    }
//
//    public Member find(Long id) {
//        return em.find(Member.class, id);
//    }
//}

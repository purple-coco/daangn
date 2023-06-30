package com.market.carrot.daangn.repository;

import com.market.carrot.daangn.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


    Member findOneById(Long id);

    Member findAllById(Long id);

    Member findByUsername(String username);


}

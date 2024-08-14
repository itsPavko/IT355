package com.it355.projekat.repository;

import com.it355.projekat.entity.Member;
import com.it355.projekat.repository.abstractrep.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends AbstractRepository<Member> {
    Member findByUserId(Integer userId);

    Member findByUser_Username(String username);
}

package com.it355.projekat.service.impl;

import com.it355.projekat.entity.Member;
import com.it355.projekat.entity.enums.MemberType;
import com.it355.projekat.repository.MemberRepository;
import com.it355.projekat.repository.abstractrep.AbstractRepository;
import com.it355.projekat.service.MemberService;
import com.it355.projekat.service.generic.impl.GenericServiceImpl;
import org.springframework.stereotype.Repository;

@Repository
public class MemberServiceImpl extends GenericServiceImpl<Member> implements MemberService {

    protected MemberServiceImpl(AbstractRepository<Member> abstractRepository) {
        super(abstractRepository);
    }

    @Override
    public Member findByUserId(Integer userId) {
        return ((MemberRepository) abstractRepository).findByUserId(userId);
    }

    @Override
    public void savePremiumMember(Integer userId) {
        Member member = findByUserId(userId);
        member.setType(MemberType.PREMIUM);
        member.setDiscount(10);
        abstractRepository.save(member);
    }

    @Override
    public Member findByUsername(String username) {
        return ((MemberRepository) abstractRepository).findByUser_Username(username);
    }
}

package com.it355.projekat.service;

import com.it355.projekat.entity.Member;
import com.it355.projekat.service.generic.GenericService;

public interface MemberService extends GenericService<Member> {

    Member findByUserId(Integer userId);

    void savePremiumMember(Integer userId);

    Member findByUsername(String username);
}

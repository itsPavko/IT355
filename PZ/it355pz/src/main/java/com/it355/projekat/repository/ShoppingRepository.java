package com.it355.projekat.repository;

import com.it355.projekat.entity.Shopping;
import com.it355.projekat.repository.abstractrep.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingRepository extends AbstractRepository<Shopping> {
    List<Shopping> findAllByMemberId(Integer memberId);
}

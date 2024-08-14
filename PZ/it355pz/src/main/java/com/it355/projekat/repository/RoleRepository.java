package com.it355.projekat.repository;

import com.it355.projekat.entity.Role;
import com.it355.projekat.repository.abstractrep.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends AbstractRepository<Role> {
    Optional<Role> findByName(String name);
}

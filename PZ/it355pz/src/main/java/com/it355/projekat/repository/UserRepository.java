package com.it355.projekat.repository;

import com.it355.projekat.entity.UserEntity;
import com.it355.projekat.repository.abstractrep.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends AbstractRepository<UserEntity> {
    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);
}

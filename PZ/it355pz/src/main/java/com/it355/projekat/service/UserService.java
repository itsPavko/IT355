package com.it355.projekat.service;

import com.it355.projekat.entity.UserEntity;
import com.it355.projekat.service.generic.GenericService;

public interface UserService extends GenericService<UserEntity> {
    UserEntity findByUsername(String username);

    UserEntity getLoggedInUser();

    UserEntity saveUser(UserEntity user);

    boolean isUserAdmin();
}

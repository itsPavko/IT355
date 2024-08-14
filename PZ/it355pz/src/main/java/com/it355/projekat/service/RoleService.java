package com.it355.projekat.service;

import com.it355.projekat.entity.Role;
import com.it355.projekat.service.generic.GenericService;

public interface RoleService extends GenericService<Role> {

    Role findByName(String name);
}

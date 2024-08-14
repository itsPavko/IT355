package com.it355.projekat.service.impl;

import com.it355.projekat.entity.Role;
import com.it355.projekat.repository.RoleRepository;
import com.it355.projekat.repository.abstractrep.AbstractRepository;
import com.it355.projekat.service.RoleService;
import com.it355.projekat.service.generic.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role> implements RoleService {

    protected RoleServiceImpl(AbstractRepository<Role> abstractRepository) {
        super(abstractRepository);
    }

    @Override
    public Role findByName(String name) {
        return ((RoleRepository) abstractRepository).findByName(name)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }
}

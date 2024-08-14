package com.it355.projekat.service.impl;

import com.it355.projekat.entity.Member;
import com.it355.projekat.entity.Role;
import com.it355.projekat.entity.UserEntity;
import com.it355.projekat.entity.enums.MemberType;
import com.it355.projekat.repository.RoleRepository;
import com.it355.projekat.repository.UserRepository;
import com.it355.projekat.repository.abstractrep.AbstractRepository;
import com.it355.projekat.security.SecurityUtil;
import com.it355.projekat.service.WalletService;
import com.it355.projekat.service.MemberService;
import com.it355.projekat.service.UserService;
import com.it355.projekat.service.generic.impl.GenericServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends GenericServiceImpl<UserEntity> implements UserService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final WalletService walletService;
    private final MemberService memberService;

    protected UserServiceImpl(AbstractRepository<UserEntity> abstractRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, WalletService walletService, MemberService memberService) {
        super(abstractRepository);
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.walletService = walletService;
        this.memberService = memberService;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return ((UserRepository) abstractRepository).findByUsername(username)
                .orElse(null);
    }

    @Override
    public UserEntity getLoggedInUser() {
        String username = SecurityUtil.getSessionUser();
        return findByUsername(username);
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        UserEntity newUser = new UserEntity();

        // set role
        Role roleUser = roleRepository.findByName(Role.USER).orElse(null);
        newUser.setRole(roleUser);

        // set credit card
        walletService.save(user.getWallet());
        newUser.setWallet(user.getWallet());

        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        abstractRepository.save(newUser);

        // set member
        memberService.save(new Member(
                newUser,
                newUser.getId(),
                0,
                MemberType.STANDARD
        ));

        return newUser;
    }

    @Override
    public boolean isUserAdmin() {
        return getLoggedInUser() != null && getLoggedInUser().getRole().getName().equals(Role.ADMIN);
    }
}

package com.it355.projekat.service.impl;

import com.it355.projekat.entity.Wallet;
import com.it355.projekat.entity.UserEntity;
import com.it355.projekat.repository.UserRepository;
import com.it355.projekat.repository.abstractrep.AbstractRepository;
import com.it355.projekat.security.SecurityUtil;
import com.it355.projekat.service.WalletService;
import com.it355.projekat.service.generic.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletServiceImpl extends GenericServiceImpl<Wallet> implements WalletService {

    private final UserRepository userRepository;

    protected WalletServiceImpl(AbstractRepository<Wallet> abstractRepository, UserRepository userRepository) {
        super(abstractRepository);
        this.userRepository = userRepository;
    }

    @Override
    public Wallet findByLoggedInUser() {
        String username = SecurityUtil.getSessionUser();
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            return userRepository.findById(user.getId())
                    .map(UserEntity::getWallet)
                    .orElse(new Wallet());
        } else {
            return new Wallet();
        }
    }
}

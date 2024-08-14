package com.it355.projekat.service;

import com.it355.projekat.entity.Wallet;
import com.it355.projekat.service.generic.GenericService;

public interface WalletService extends GenericService<Wallet> {
    Wallet findByLoggedInUser();
}

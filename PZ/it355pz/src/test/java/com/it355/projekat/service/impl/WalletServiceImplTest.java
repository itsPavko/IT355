package com.it355.projekat.service.impl;

import com.it355.projekat.entity.Wallet;
import com.it355.projekat.entity.Role;
import com.it355.projekat.entity.UserEntity;
import com.it355.projekat.repository.WalletRepository;
import com.it355.projekat.repository.RoleRepository;
import com.it355.projekat.repository.UserRepository;
import com.it355.projekat.service.WalletService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = {WalletServiceImpl.class})
public class WalletServiceImplTest {
    @MockBean
    private WalletRepository walletRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RoleRepository roleRepository;
    @Autowired
    private WalletService walletService;

    private static final String USERNAME = "test";

    private UserEntity user;
    private Role role;
    private Wallet wallet;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        role = new Role(
                1,
                "USER"
        );
        when(roleRepository.save(any(Role.class))).thenReturn(role);

        wallet = new Wallet(
                1,
                "123",
                "Bank",
                2000.00
        );
        when(walletRepository.save(any(Wallet.class))).thenReturn(wallet);

        user = new UserEntity(
                1,
                role,
                wallet,
                USERNAME,
                "test"
        );
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        walletRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    public void testFindByLoggedInUser_WithExistingUser() {
        // Set up the SecurityContext with the authentication
        Authentication authentication = new UsernamePasswordAuthenticationToken(USERNAME, null);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.ofNullable(user));
        when(walletRepository.findById(wallet.getId())).thenReturn(Optional.ofNullable(wallet));

//        CreditCard result = creditCardService.findByLoggedInUser();
//
//        assertNotNull(result);
//        assertEquals(creditCard, result);
    }

}

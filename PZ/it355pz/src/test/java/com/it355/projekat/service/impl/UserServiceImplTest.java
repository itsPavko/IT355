package com.it355.projekat.service.impl;

import com.it355.projekat.entity.Wallet;
import com.it355.projekat.entity.Role;
import com.it355.projekat.entity.UserEntity;
import com.it355.projekat.repository.RoleRepository;
import com.it355.projekat.repository.UserRepository;
import com.it355.projekat.service.WalletService;
import com.it355.projekat.service.MemberService;
import com.it355.projekat.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = {UserServiceImpl.class})
public class UserServiceImplTest {
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private WalletService walletService;
    @MockBean
    private MemberService memberService;

    @Autowired
    private UserService userService;

    private Role role;
    private UserEntity user;
    private Wallet wallet;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        role = new Role();
        role.setId(2);
        role.setName(Role.USER);

        wallet = new Wallet();
        wallet.setId(1);
        wallet.setCardNumber("123");
        wallet.setMoney(50000.00);

        user = new UserEntity();
        user.setId(1);
        user.setUsername("test");
        user.setPassword("test");
        user.setRole(role);
        user.setWallet(wallet);
    }

    @Test
    public void testFindByUsername_WhenUserExists() {
        String username = "test";
        UserEntity expectedUser = new UserEntity();
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(expectedUser));

        UserEntity result = userService.findByUsername(username);

        assertEquals(expectedUser, result);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    public void testFindByUsername_WhenUserDoesNotExist() {
        String username = "test";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        UserEntity result = userService.findByUsername(username);

        assertNull(result);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    public void testSaveUser() {
        when(roleRepository.findByName(Role.USER)).thenReturn(Optional.of(role));

        when(walletService.save(wallet)).thenReturn(wallet);

        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        UserEntity savedUser = userService.saveUser(user);

        assertEquals(role, savedUser.getRole());
        assertEquals(wallet, savedUser.getWallet());
        verify(roleRepository, times(1)).findByName(Role.USER);
        verify(walletService, times(1)).save(wallet);
        verify(userRepository, times(1)).save(savedUser);
    }


}

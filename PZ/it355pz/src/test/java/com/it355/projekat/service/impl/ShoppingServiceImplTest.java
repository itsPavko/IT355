package com.it355.projekat.service.impl;


import com.it355.projekat.entity.enums.MemberType;
import com.it355.projekat.exception.NotEnoughMoneyException;
import com.it355.projekat.service.WatchService;
import com.it355.projekat.service.MemberService;
import com.it355.projekat.service.ShoppingService;
import com.it355.projekat.entity.*;
import com.it355.projekat.repository.*;
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

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = {ShoppingServiceImpl.class})
public class ShoppingServiceImplTest {
    @MockBean
    private ShoppingRepository shoppingRepository;

    @MockBean
    private MemberRepository memberRepository;

    @MockBean
    private MemberService memberService;

    @MockBean
    private WatchService watchService;

    @MockBean
    private WatchRepository watchRepository;

    @MockBean
    private WalletRepository walletRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CompanyRepository companyRepository;

    @Autowired
    private ShoppingService shoppingService;

    private static final String USERNAME = "test";

    Watch watch;
    Member member;
    UserEntity user;
    Wallet wallet;
    Role role;
    Company company;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Test data
        company = new Company(
                1,
                "John",
                "Doe",
                35,
                "SRB"
        );
        when(companyRepository.save(any(Company.class))).thenReturn(company);

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
                "test",
                "test"
        );
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        watch = new Watch(
                1,
                company,
                "Test Book",
                1000.00,
                5
        );
        when(watchRepository.save(any(Watch.class))).thenReturn(watch);

        member = new Member(
                1,
                user,
                125,
                10,
                MemberType.PREMIUM
        );
        when(memberRepository.save(any(Member.class))).thenReturn(member);
    }

    @AfterEach
    public void tearDown() {
        watchRepository.deleteAll();
        memberRepository.deleteAll();
        userRepository.deleteAll();
        walletRepository.deleteAll();
        roleRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    public void testSavePurchase_WithSufficientBalance() {
        // Create a mock Purchase object with a valid Book object
        Shopping purchase = new Shopping();
        purchase.setWatch(watch);

        // Mock the behavior of the memberService
        when(memberService.findByUserId(anyInt())).thenReturn(member);

        // Mock the behavior of the bookService
        when(watchService.findById(anyInt())).thenReturn(watch);

        // Mock the behavior of the genericRepository.save() method
        when(shoppingRepository.save(any(Shopping.class))).thenReturn(purchase);

        int initialAmount = watch.getAmount();
        double initialBalance = member.getUser().getWallet().getMoney();

        Shopping savedPurchase = shoppingService.saveShopping(user.getId(), watch.getId());

        assertNotNull(savedPurchase);  // Ensure that the purchase is not null

        assertEquals(initialAmount - 1, savedPurchase.getWatch().getAmount());
        double expectedPrice = watch.getPrice();
        assertEquals(initialBalance - expectedPrice, member.getUser().getWallet().getMoney());

        // Verify that the necessary methods were called
        verify(memberService, times(1)).findByUserId(anyInt());
        verify(watchService, times(1)).findById(anyInt());
        verify(watchService, times(1)).save(any(Watch.class));
        verify(memberService, times(1)).save(any(Member.class));
        verify(shoppingRepository, times(1)).save(any(Shopping.class));
    }


    @Test
    public void testSavePurchase_WithInsufficientBalance() {
        // Set the member's credit card balance to an insufficient amount
        member.getUser().getWallet().setMoney(0.0);

        // Mock the behavior of the memberService
        when(memberService.findByUserId(anyInt())).thenReturn(member);

        // Mock the behavior of the bookService
        when(watchService.findById(anyInt())).thenReturn(watch);

        assertThrows(NotEnoughMoneyException.class, () -> shoppingService.saveShopping(user.getId(), watch.getId()));

        // Verify that no changes were made
        assertEquals(5, watch.getAmount());
        assertEquals(0.0, member.getUser().getWallet().getMoney());

        // Verify that the necessary methods were called
        verify(memberService, times(1)).findByUserId(anyInt());
        verify(watchService, times(1)).findById(anyInt());
        verifyNoMoreInteractions(watchService);
        verifyNoMoreInteractions(memberService);
        verifyNoMoreInteractions(shoppingRepository);
    }

    @Test
    public void testFindAllByLoggedInMember() {
        // Set up the SecurityContext with the authentication
        Authentication authentication = new UsernamePasswordAuthenticationToken(USERNAME, null);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Mock the behavior of the memberService.findByUsername() method
        when(memberService.findByUsername(USERNAME)).thenReturn(member);

        // Mock the behavior of the purchaseRepository.findAllByMemberId() method
        when(shoppingRepository.findAllByMemberId(member.getId())).thenReturn(Collections.emptyList());

        List<Shopping> purchases = shoppingService.findAllByLoggedInMember();

        assertNotNull(purchases);
        assertEquals(0, purchases.size());

        // Verify that the necessary methods were called
        verify(memberService, times(1)).findByUsername(anyString());
        verify(shoppingRepository, times(1)).findAllByMemberId(anyInt());

        // Reset the SecurityContext after the test
        SecurityContextHolder.clearContext();
    }
}

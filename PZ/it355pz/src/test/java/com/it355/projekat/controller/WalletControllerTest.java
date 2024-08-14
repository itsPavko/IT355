package com.it355.projekat.controller;

import com.it355.projekat.entity.Wallet;
import com.it355.projekat.service.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = {WalletController.class})
class WalletControllerTest {
    @MockBean
    private WalletService walletService;
    @MockBean
    private Model model;
    @MockBean
    private BindingResult bindingResult;

    @Autowired
    private WalletController walletController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCreditCardsPage() {
        Wallet wallet = new Wallet();
        when(walletService.findByLoggedInUser()).thenReturn(wallet);

        String result = walletController.getCreditCardsPage(model);

        assertEquals("credit-card/update-credit-card", result);
        verify(model, times(1)).addAttribute("creditCard", wallet);
    }

    @Test
    void testUpdateCreditCard_WithValidData() {
        Wallet wallet = new Wallet();
        int creditCardId = 1;

        when(bindingResult.hasErrors()).thenReturn(false);

        String result = walletController.updateCreditCard(wallet, creditCardId, bindingResult);

        assertEquals("redirect:/watches", result);
        verify(walletService, times(1)).update(wallet);
        assertEquals(creditCardId, wallet.getId());
    }

    @Test
    void testUpdateCreditCard_WithInvalidData() {
        Wallet wallet = new Wallet();
        int creditCardId = 1;

        when(bindingResult.hasErrors()).thenReturn(true);

        try {
            walletController.updateCreditCard(wallet, creditCardId, bindingResult);
        } catch (RuntimeException e) {
            assertEquals("Credit card data are invalid!", e.getMessage());
        }

        verifyNoInteractions(walletService);
    }
}
package com.it355.projekat.controller;


import com.it355.projekat.entity.Shopping;
import com.it355.projekat.exception.NotEnoughMoneyException;
import com.it355.projekat.service.MemberService;
import com.it355.projekat.service.ShoppingService;
import com.it355.projekat.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = {ShoppingController.class})
class ShoppingControllerTest {
    @MockBean
    private ShoppingService shoppingService;
    @MockBean
    private MemberService memberService;
    @MockBean
    private UserService userService;
    @MockBean
    private Model model;
    @Autowired
    private ShoppingController shoppingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPurchasesPage() {
        List<Shopping> purchases = new ArrayList<>();
        purchases.add(new Shopping());
        purchases.add(new Shopping());
        when(shoppingService.findAll()).thenReturn(purchases);

        String result = shoppingController.getShoppingsPage(model);

        assertEquals("shopping/shoppings", result);
        verify(model, times(1)).addAttribute("shoppings", purchases);
    }

    @Test
    void testSavePurchase() {
        int watchId = 1;
        int userId = 1;

        String result = shoppingController.saveShopping(watchId, userId);

        assertEquals("redirect:/shoppings/my-shoppings", result);
        verify(shoppingService, times(1)).saveShopping(userId, watchId);
    }

    @Test
    void testSavePurchase_NotEnoughMoneyException() {
        int watchId = 1;
        int userId = 1;
        Mockito.doThrow(NotEnoughMoneyException.class).when(shoppingService).saveShopping(userId, watchId);

        try {
            shoppingController.saveShopping(watchId, userId);
        } catch (RuntimeException ex) {
            assertEquals("Not enough money!", ex.getMessage());
        }
    }

}

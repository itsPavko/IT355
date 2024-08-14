package com.it355.projekat.controller;

import com.it355.projekat.exception.NotEnoughMoneyException;
import com.it355.projekat.service.MemberService;
import com.it355.projekat.service.ShoppingService;
import com.it355.projekat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Controller
@RequiredArgsConstructor
@RequestMapping("/shoppings")
public class ShoppingController {
    private final ShoppingService shoppingService;
    private final MemberService memberService;
    private final UserService userService;

    //PAGES START
    @GetMapping("")
    public String getShoppingsPage(Model model) {
        model.addAttribute("shoppings", shoppingService.findAll());
        return "shopping/shoppings";
    }
    //PAGES END

    @PostMapping("/save")
    public String saveShopping(@RequestParam("watchId") Integer watchId,
                               @RequestParam("userId") Integer userId) {
        try {
            shoppingService.saveShopping(userId, watchId);
            return "redirect:/shoppings/my-shoppings";
        } catch (NotEnoughMoneyException ex) {
            throw new RuntimeException("Not enough money!");
        }
    }

    @GetMapping("/my-shoppings")
    public String getMyShoppingsPage(Model model) {
        model.addAllAttributes(Map.of(
                "shoppings", shoppingService.findAllByLoggedInMember(),
                "member", memberService.findByUserId(userService.getLoggedInUser().getId())
        ));
        return "shopping/my-shoppings";
    }
}

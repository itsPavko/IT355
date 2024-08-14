package com.it355.projekat.controller;

import com.it355.projekat.entity.Wallet;
import com.it355.projekat.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/credit-cards")
public class WalletController {
    private final WalletService walletService;

    //PAGES START
    @GetMapping("")
    public String getCreditCardsPage(Model model) {
        model.addAttribute("wallet", walletService.findByLoggedInUser());
        return "credit-card/update-credit-card";
    }
    //PAGES END

    @PostMapping("/update")
    public String updateCreditCard(@Valid @ModelAttribute("wallet") Wallet wallet,
                                   @RequestParam("walletId") Integer walletId,
                                   BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException("Credit card data are invalid!");
        }
        wallet.setId(walletId);
        walletService.update(wallet);

        return "redirect:/watches";
    }
}

package com.it355.projekat.controller;

import com.it355.projekat.entity.UserEntity;
import com.it355.projekat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final UserService userService;

    // ATTRIBUTES
    @ModelAttribute("isAdmin")
    public boolean isAdmin() {
        return userService.isUserAdmin();
    }

    @ModelAttribute("loggedInUser")
    public UserEntity getLoggedInUser() {
        return userService.getLoggedInUser();
    }

    // EXCEPTION HANDLING
    @ExceptionHandler(RuntimeException.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "ERROR: " + ex.getMessage());
        return "error/error";
    }
}

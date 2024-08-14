package com.it355.projekat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "You don't have enough money")
public class NotEnoughMoneyException extends RuntimeException {
}

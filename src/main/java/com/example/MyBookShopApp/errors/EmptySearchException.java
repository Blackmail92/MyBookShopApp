package com.example.MyBookShopApp.errors;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class EmptySearchException extends Exception {
    public EmptySearchException(String message) {
        super(message);
    }
}

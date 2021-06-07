package com.example.MyBookShopApp.errors;

public class BookStoreApiException extends Exception {
    public BookStoreApiException(String message) {
        super(message);
    }
}

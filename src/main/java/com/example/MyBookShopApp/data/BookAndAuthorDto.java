package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.entities.simple.Author;
import com.example.MyBookShopApp.data.entities.simple.Book;
import lombok.Getter;

@Getter
public class BookAndAuthorDto {
    private final Book book;
    private final Author author;

    public BookAndAuthorDto(Book book, Author author) {
        this.book = book;
        this.author = author;
    }
}

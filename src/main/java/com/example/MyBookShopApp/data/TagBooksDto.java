package com.example.MyBookShopApp.data;

import lombok.Data;

import java.util.List;

@Data
public class TagBooksDto {
    private String tag;
    private List<BookDto> books;

    public TagBooksDto(String tag, List<BookDto> books) {
        this.tag = tag;
        this.books = books;
    }
}

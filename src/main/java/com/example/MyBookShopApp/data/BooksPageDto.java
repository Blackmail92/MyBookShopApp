package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.entities.simple.Book;
import lombok.Data;

import java.util.List;

@Data
public class BooksPageDto {
    private Integer count;
    private List<BookDto> books;

    public BooksPageDto(List<BookDto> books) {
        this.books = books;
        this.count = books.size();
    }
}

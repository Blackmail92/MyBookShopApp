package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BookDto;
import com.example.MyBookShopApp.data.BooksAndAuthorsService;
import com.example.MyBookShopApp.data.SearchWordDto;
import com.example.MyBookShopApp.data.entities.simple.Author;
import com.example.MyBookShopApp.data.entities.simple.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/books")
public class AuthorSlugController {
    private final BooksAndAuthorsService service;

    @Autowired
    public AuthorSlugController(BooksAndAuthorsService service) {
        this.service = service;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @GetMapping("/authors/{id}")
    public String getAuthor(@PathVariable Integer id, Model model) {
        Author author = service.getAuthorById(id);
        model.addAttribute("authorById", author);
        model.addAttribute("booksByAuthor", service.getBooksByAuthor(author));
        return "authors/slug";
    }

    @GetMapping("/author/{id}")
    public String booksByAuthor(@PathVariable Integer id, Model model) {
        Author author = service.getAuthorById(id);
        model.addAttribute("authorById", author);
        List<BookDto> books = service.getBooksByAuthor(author);
        if (books.size() > 0) {
            model.addAttribute("booksByAuthor", service.getBooksByAuthor(author));
        } else {
            model.addAttribute("booksByAuthor", Collections.EMPTY_LIST);
        }
        return "books/author";
    }
}

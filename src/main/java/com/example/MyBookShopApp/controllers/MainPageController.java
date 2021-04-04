package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BookAndAuthorDto;
import com.example.MyBookShopApp.data.BooksAndAuthorsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class MainPageController {
    private Logger logger = LoggerFactory.getLogger(MainPageController.class);
    private final BooksAndAuthorsService bookService;

    @Autowired
    public MainPageController(BooksAndAuthorsService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("allBooks")
    public List<BookAndAuthorDto> allBooks() {
        return bookService.getAllBooksAndAuthors();
    }


    @ModelAttribute("recommendedBooks")
    public List<BookAndAuthorDto> recommendedBooks() {
        return bookService.getRecommendedBooksAndAuthors();
    }

    @ModelAttribute("recentBooks")
    public List<BookAndAuthorDto> recentBooks() {
        return bookService.getRecentBooksAndAuthors();
    }

    @ModelAttribute("popularBooks")
    public List<BookAndAuthorDto> popularBooks() {
        return bookService.getPopularBooksAndAuthors();
    }


    @GetMapping
    public String mainPage() {
        return "index";
    }

}
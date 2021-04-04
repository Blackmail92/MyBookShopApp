package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BooksAndAuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class PopularController {

    private final BooksAndAuthorsService bookService;

    @Autowired
    public PopularController(BooksAndAuthorsService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/popular")
    public String popular(Model model) {
        model.addAttribute("popularBooks", bookService.getPopularBooksAndAuthors());
        return "/books/popular";
    }
}

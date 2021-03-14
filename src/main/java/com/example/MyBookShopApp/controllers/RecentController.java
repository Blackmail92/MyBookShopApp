package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BooksAndAuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RecentController {

    private final BooksAndAuthorsService bookService;

    @Autowired
    public RecentController(BooksAndAuthorsService bookService) {
        this.bookService = bookService;
    }

/*
    @GetMapping("/recent")
    public String recent(Model model) {
        model.addAttribute("recentBooks", bookService.getRecentBooks());
        return "/books/recent";
    }
*/
}

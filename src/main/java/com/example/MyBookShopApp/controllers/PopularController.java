package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BooksAndAuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PopularController {

    private final BooksAndAuthorsService bookService;

    @Autowired
    public PopularController(BooksAndAuthorsService bookService) {
        this.bookService = bookService;
    }

/*    @GetMapping("/popular")
    public String popular(Model model) {
        model.addAttribute("popularBooks", bookService.getRecommendedBooks());
        return "/books/popular";
    }*/
}

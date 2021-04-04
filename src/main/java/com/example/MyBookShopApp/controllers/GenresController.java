package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BooksAndAuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class GenresController {

    private final BooksAndAuthorsService bookService;

    @Autowired
    public GenresController(BooksAndAuthorsService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/genres")
    public String genresPage() {
        return "genres/index";
    }

/*    @GetMapping("/genres/slug")
    public String slug(Model model) {
        model.addAttribute("genreBooks", bookService.getRecommendedBooks());
        return "/genres/slug";
    }*/
}

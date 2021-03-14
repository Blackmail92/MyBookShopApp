package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BooksAndAuthorsService;
import com.example.MyBookShopApp.data.entities.simple.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;

@Controller
public class AuthorsController {

    private final BooksAndAuthorsService authorsService;

    @Autowired
    public AuthorsController(BooksAndAuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @ModelAttribute("authors")
    public Map<String, List<Author>> authors() {
        return authorsService.getAllAuthorsGrouped();
    }

    @GetMapping("/authors")
    public String authorsPage() {
        return "authors/index";
    }
}

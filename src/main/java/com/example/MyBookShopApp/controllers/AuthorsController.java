package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BooksAndAuthorsService;
import com.example.MyBookShopApp.data.SearchWordDto;
import com.example.MyBookShopApp.data.entities.simple.Author;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/books")
@Api(description = "Authors data")
public class AuthorsController {

    private final BooksAndAuthorsService authorsService;

    @Autowired
    public AuthorsController(BooksAndAuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("authors")
    public Map<String, List<Author>> authorsMap() {
        return authorsService.getAllAuthorsGrouped();
    }

    @GetMapping("/authors")
    public String authorsPage() {
        return "authors/index";
    }

    @GetMapping("/api/authors")
    public Map<String, List<Author>> authors() {
        return authorsService.getAllAuthorsGrouped();
    }
}

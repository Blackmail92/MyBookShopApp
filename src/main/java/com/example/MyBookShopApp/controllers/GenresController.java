package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BooksAndAuthorsService;
import com.example.MyBookShopApp.data.GenresService;
import com.example.MyBookShopApp.data.SearchWordDto;
import com.example.MyBookShopApp.data.repos.GenresRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class GenresController {

    private final GenresService genresService;
    private final BooksAndAuthorsService booksService;

    @Autowired
    public GenresController(GenresService genresService, BooksAndAuthorsService booksService) {
        this.genresService = genresService;
        this.booksService = booksService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @GetMapping("/genres")
    public String genresPage(Model model) {
        model.addAttribute("genresList", genresService.getAllGenres());
        return "genres/index";
    }

    @GetMapping("/genres/{id}")
    public String slug(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("genre", genresService.getById(id));
        model.addAttribute("genreBooks", booksService.getBooksByGenre(id, 0, 20));
        return "/genres/slug";
    }
}

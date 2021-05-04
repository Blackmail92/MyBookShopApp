package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BooksAndAuthorsService;
import com.example.MyBookShopApp.data.SearchWordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookSlugController {
    private final BooksAndAuthorsService bookService;

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @Autowired
    public BookSlugController(BooksAndAuthorsService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{slug}")
    public String getBook(@PathVariable("slug") String slug, Model model) {
        model.addAttribute("bookBySlug", bookService.getBookBySlug(slug));
        return "books/slug";
    }
}

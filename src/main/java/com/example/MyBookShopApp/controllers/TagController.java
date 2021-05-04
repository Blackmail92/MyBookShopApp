package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BooksAndAuthorsService;
import com.example.MyBookShopApp.data.SearchWordDto;
import com.example.MyBookShopApp.data.TagBooksDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tag")
public class TagController {
    private final BooksAndAuthorsService bookService;

    public TagController(BooksAndAuthorsService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @GetMapping("/{tag}")
    public String byTag(@PathVariable("tag")String tag, Model model) {
        model.addAttribute("booksByTag", new TagBooksDto(tag, bookService.getSearchResultBooks(tag, 0, 20)));
        return "tags/index";
    }
}

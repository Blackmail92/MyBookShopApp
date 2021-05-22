package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BooksAndAuthorsService;
import com.example.MyBookShopApp.data.ResourceStorage;
import com.example.MyBookShopApp.data.SearchWordDto;
import com.example.MyBookShopApp.data.entities.simple.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/book")
public class BookSlugController {
    private final BooksAndAuthorsService bookService;
    private final ResourceStorage storage;

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @Autowired
    public BookSlugController(BooksAndAuthorsService bookService, ResourceStorage storage) {
        this.bookService = bookService;
        this.storage = storage;
    }

    @GetMapping("/{slug}")
    public String getBook(@PathVariable("slug") String slug, Model model) {
        model.addAttribute("bookBySlug", bookService.getBookDtoBySlug(slug));
        return "books/slug";
    }

    @PostMapping("/{slug}/img/save")
    public String saveNewBookImage(@RequestParam("file")MultipartFile file, @PathVariable("slug") String slug) throws IOException {
        String savePath = storage.saveNewBookImage(file, slug);
        Book bookToUpdate = bookService.getBookBySlug(slug);
        bookToUpdate.setImage(savePath);
        bookService.getBookRepo().save(bookToUpdate);
        return ("redirect:/book/" + slug);
    }
}

package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BooksAndAuthorsService;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/books")
public class RecentController {
    private Logger log = LoggerFactory.getLogger(RecentController.class);

    private final BooksAndAuthorsService bookService;

    @Autowired
    public RecentController(BooksAndAuthorsService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("pair")
    public Pair<String, String> pair() {
        return new Pair<>("", "");
    }

    @GetMapping("/recent")
    public String recent(@RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") Date from,
                         @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") Date to,
                         Model model) {
        log.warn("from: {}, to: {}", from, to);
        model.addAttribute("recentBooks", bookService.getBooksByDate(from, to));
        return "/books/recent";
    }
}

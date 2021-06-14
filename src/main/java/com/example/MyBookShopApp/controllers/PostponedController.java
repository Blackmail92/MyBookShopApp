package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BookDto;
import com.example.MyBookShopApp.data.BooksAndAuthorsService;
import com.example.MyBookShopApp.data.SearchWordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/postponed")
public class PostponedController {

    private final BooksAndAuthorsService service;

    @Autowired
    public PostponedController(BooksAndAuthorsService service) {
        this.service = service;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @GetMapping
    public String postponed(@CookieValue(value = "postponed", required = false) String postponed, Model model) {
        if (!StringUtils.hasLength(postponed)) {
            model.addAttribute("isPostponedEmpty", true);
        } else {
            model.addAttribute("isPostponedEmpty", false);
            List<String> slugs = Arrays.asList(postponed.split("/"));
            slugs = slugs.stream().filter(StringUtils::hasLength).collect(Collectors.toList());
            List<BookDto> books = service.getBooksBySlugIn(slugs);
            model.addAttribute("postponedBooks", books);
        }
        return "postponed";
    }

    @PostMapping("/changeBookStatus/remove/{slug}")
    public String removeBook(@PathVariable("slug") String slug, @CookieValue(name = "postponed", required = false) String postponed, HttpServletResponse response, Model model) {
        if (StringUtils.hasLength(postponed)) {
            ArrayList<String> books = new ArrayList<>(Arrays.asList(postponed.split("/")));
            books.remove(slug);
            Cookie cookie = new Cookie("postponed", String.join("/", books));
            cookie.setPath("/postponed");
            response.addCookie(cookie);
            model.addAttribute("isPostponedEmpty", books.size() == 0);
        } else {
            model.addAttribute("isPostponedEmpty", true);
        }
        return "redirect:/postponed";
    }

    @PostMapping("/changeBookStatus/{slug}")
    public String addBook(@PathVariable("slug") String slug, @CookieValue(name = "postponed", required = false) String postponed, HttpServletResponse response, Model model) {
        if (!StringUtils.hasLength(postponed)) {
            Cookie cookie = new Cookie("postponed", slug);
            cookie.setPath("/postponed");
            response.addCookie(cookie);
            model.addAttribute("isPostponedEmpty", false);
        } else if (!postponed.contains(slug)) {
            StringJoiner joiner = new StringJoiner("/");
            joiner.add(postponed).add(slug);
            Cookie cookie = new Cookie("postponed", joiner.toString());
            cookie.setPath("/postponed");
            response.addCookie(cookie);
            model.addAttribute("isPostponedEmpty", false);
        }
        return "redirect:/book/" + slug;
    }
}

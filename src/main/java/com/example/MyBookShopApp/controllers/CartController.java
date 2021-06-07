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
@RequestMapping("/cart")
public class CartController {

    private final BooksAndAuthorsService service;

    @Autowired
    public CartController(BooksAndAuthorsService service) {
        this.service = service;
    }

    @ModelAttribute("bookCart")
    public List<BookDto> bookCart() {
        return new ArrayList<>();
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @GetMapping
    public String cartPage(@CookieValue(value = "cartContents", required = false) String cartContents, Model model) {
        if (!StringUtils.hasLength(cartContents)) {
            model.addAttribute("isCartEmpty", true);
        } else {
            model.addAttribute("isCartEmpty", false);
            List<String> slugs = Arrays.asList(cartContents.split("/"));
            slugs = slugs.stream().filter(StringUtils::hasLength).collect(Collectors.toList());
            List<BookDto> books = service.getBooksBySlugIn(slugs);
            model.addAttribute("bookCart", books);
            model.addAttribute("totalPrice", books.stream().mapToInt(BookDto::getPrice).sum());
            model.addAttribute("totalDiscPrice", books.stream().mapToInt(BookDto::getDiscountPrice).sum());
        }
        return "cart";
    }

    @PostMapping("/changeBookStatus/remove/{slug}")
    public String removeBook(@PathVariable("slug") String slug, @CookieValue(name = "cartContents", required = false) String cartContents, HttpServletResponse response, Model model) {
        if (StringUtils.hasLength(cartContents)) {
            ArrayList<String> books = new ArrayList<>(Arrays.asList(cartContents.split("/")));
            books.remove(slug);
            Cookie cookie = new Cookie("cartContents", String.join("/", books));
            cookie.setPath("/cart");
            response.addCookie(cookie);
            model.addAttribute("isCartEmpty", books.size() == 0);
        } else {
            model.addAttribute("isCartEmpty", true);
        }
        return "redirect:/cart";
    }

    @PostMapping("/changeBookStatus/{slug}")
    public String addBook(@PathVariable("slug") String slug, @CookieValue(name = "cartContents", required = false) String cartContents, HttpServletResponse response, Model model) {
        if (!StringUtils.hasLength(cartContents)) {
            Cookie cookie = new Cookie("cartContents", slug);
            cookie.setPath("/cart");
            response.addCookie(cookie);
            model.addAttribute("isCartEmpty", false);
        } else if (!cartContents.contains(slug)) {
            StringJoiner joiner = new StringJoiner("/");
            joiner.add(cartContents).add(slug);
            Cookie cookie = new Cookie("cartContents", joiner.toString());
            cookie.setPath("/cart");
            response.addCookie(cookie);
            model.addAttribute("isCartEmpty", false);
        }
        return "redirect:/book/" + slug;
    }
}

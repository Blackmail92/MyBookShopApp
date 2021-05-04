package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BookDto;
import com.example.MyBookShopApp.data.BooksAndAuthorsService;
import com.example.MyBookShopApp.data.BooksPageDto;
import com.example.MyBookShopApp.data.SearchWordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final BooksAndAuthorsService bookService;

    @Autowired
    public SearchController(BooksAndAuthorsService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<BookDto> searchResults() {
        return new ArrayList<>();
    }

    @GetMapping(value = {"/", "/{searchWord}"})
    public String getSearchResult(@PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto, Model model) {
        if (Objects.nonNull(searchWordDto)) {
            model.addAttribute("searchWordDto", searchWordDto);
            model.addAttribute("searchResults", bookService.getSearchResultBooks(searchWordDto.getExample(), 0, 20));
        } else {
            model.addAttribute("searchResults", bookService.getSearchResultBooks("", 0, 20));
        }
        return "/search/index";
    }

    @GetMapping("/page/{searchWord}")
    @ResponseBody
    public BooksPageDto getSearchPage(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit,
                                      @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto) {
        return new BooksPageDto(bookService.getSearchResultBooks(searchWordDto.getExample(), offset, limit));
    }
}

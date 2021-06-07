package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.ApiResponse;
import com.example.MyBookShopApp.data.BookDto;
import com.example.MyBookShopApp.data.BooksAndAuthorsService;
import com.example.MyBookShopApp.data.entities.simple.Author;
import com.example.MyBookShopApp.errors.BookStoreApiException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class BookRestApiController {
    private final BooksAndAuthorsService service;

    @Autowired
    public BookRestApiController(BooksAndAuthorsService service) {
        this.service = service;
    }

    @GetMapping("/books/by-author")
    @ApiOperation("get books by author")
    public ResponseEntity<ApiResponse<BookDto>> booksByAuthor(@RequestParam("author") String  authorName) {
        ApiResponse<BookDto> resp = new ApiResponse<>();
        List<BookDto> data = service.getBooksByAuthor(service.getAuthorByName(authorName));
        resp.setDebugMessage("success");
        resp.setMessage("data size: " + data.size());
        resp.setStatus(HttpStatus.OK);
        resp.setData(data);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/books/by-title")
    @ApiOperation("get books by title")
    public ResponseEntity<ApiResponse<BookDto>> booksByTitle(@RequestParam("title") String title) throws BookStoreApiException {
        ApiResponse<BookDto> resp = new ApiResponse<>();
        List<BookDto> data = service.getBooksByTitle(title);
        resp.setDebugMessage("success");
        resp.setMessage("data size: " + data.size());
        resp.setStatus(HttpStatus.OK);
        resp.setData(data);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/books/bestsellers")
    @ApiOperation("get bestsellers")
    public ResponseEntity<ApiResponse<BookDto>> bestsellers() {
        ApiResponse<BookDto> resp = new ApiResponse<>();
        List<BookDto> data = service.getBestsellers();
        resp.setDebugMessage("success");
        resp.setMessage("data size: " + data.size());
        resp.setStatus(HttpStatus.OK);
        resp.setData(data);
        return ResponseEntity.ok(resp);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<BookDto>> handleServletException(Exception ex) {
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.BAD_REQUEST, "Missing required parameters", ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookStoreApiException.class)
    public ResponseEntity<ApiResponse<BookDto>> handleApiException(Exception ex) {
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.BAD_REQUEST, "Bad param value", ex), HttpStatus.BAD_REQUEST);
    }
}

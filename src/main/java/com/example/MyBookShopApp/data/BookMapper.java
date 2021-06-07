package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.entities.simple.Book;
import com.example.MyBookShopApp.data.entities.simple.BookFile;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {
    public static BookDto from(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .pubDate(book.getPubDate())
                .isBestseller(book.isBestseller())
                .slug(book.getSlug())
                .title(book.getTitle())
                .image(book.getImage())
                .description(book.getDescription())
                .price(book.getPrice())
                .discount(book.getDiscount())
                .discountPrice(book.discountPrice())
                .author(book.getAuthor().getName())
                .authorId(book.getAuthor().getId())
                .fileList(book.getFiles())
                .build();
    }

    public static List<BookDto> fromList(List<Book> bookList) {
        return bookList.stream()
                .map(BookMapper::from)
                .collect(Collectors.toList());
    }
}

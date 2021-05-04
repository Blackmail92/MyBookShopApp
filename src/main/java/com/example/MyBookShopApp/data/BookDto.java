package com.example.MyBookShopApp.data;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class BookDto {
    private Integer id;
    private LocalDateTime pubDate;
    private boolean isBestseller;
    private String slug;
    private String title;
    private String image;
    private String description;
    private Integer price;
    private short discount;
    private String author;
    private Integer authorId;
}

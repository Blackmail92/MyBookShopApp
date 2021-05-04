package com.example.MyBookShopApp.data;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GenreDto {
    private Integer id;
    private String name;
    private Integer booksCount;
}

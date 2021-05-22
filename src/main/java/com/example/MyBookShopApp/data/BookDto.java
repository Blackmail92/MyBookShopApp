package com.example.MyBookShopApp.data;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class BookDto {
    private Integer id;
    private LocalDateTime pubDate;
    @JsonProperty
    private boolean isBestseller;
    @JsonProperty
    private String slug;
    private String title;
    private String image;
    private String description;
    @JsonProperty
    private Integer price;
    @JsonProperty
    private short discount;
    @JsonProperty
    private Integer discountPrice;
    private String author;
    private Integer authorId;

    @JsonGetter("authors")
    public String authors() {
        return author;
    }
}

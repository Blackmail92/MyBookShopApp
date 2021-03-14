package com.example.MyBookShopApp.data.entities.connections;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class BookToAuthorPK implements Serializable {
    private Integer book;
    private Integer author;
}

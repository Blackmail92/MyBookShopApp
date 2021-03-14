package com.example.MyBookShopApp.data.entities.connections;

import com.example.MyBookShopApp.data.entities.simple.Author;
import com.example.MyBookShopApp.data.entities.simple.Book;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "book2author")
@IdClass(BookToAuthorPK.class)
public class BookToAuthor implements Serializable {
    @Id
    @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY)
    private Book book;

    @Id
    @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY)
    private Author author;

    @Column(columnDefinition = "INT NOT NULL DEFAULT 0")
    private Integer sortIndex;
}

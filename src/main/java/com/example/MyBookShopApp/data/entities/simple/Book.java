package com.example.MyBookShopApp.data.entities.simple;

import com.example.MyBookShopApp.data.entities.connections.BookToAuthor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT NOT NULL")
    private Integer id;

    @Column(columnDefinition = "DATE NOT NULL")
    private LocalDate pubDate;

    @Column(columnDefinition = "BOOLEAN NOT NULL")
    private boolean isBestseller;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String slug;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String title;

    @Column(columnDefinition = "VARCHAR(255)")
    private String image;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "INT NOT NULL")
    private Integer price;

    @Column(columnDefinition = "SMALLINT NOT NULL DEFAULT 0")
    private short discount;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<BookToAuthor> author;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<BookReview> bookReview;

    @ManyToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Genre> genre;
}

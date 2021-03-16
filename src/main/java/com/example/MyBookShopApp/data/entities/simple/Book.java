package com.example.MyBookShopApp.data.entities.simple;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private LocalDateTime pubDate;

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

    @ManyToMany(targetEntity = Author.class, fetch = FetchType.LAZY)
    @JoinTable(name = "book2author")
    private List<Author> author;

    @OneToMany(targetEntity = BookReview.class, fetch = FetchType.LAZY)
    @JoinTable(name = "book2review")
    private List<BookReview> bookReview;

    @ManyToMany(targetEntity = Genre.class, fetch = FetchType.LAZY)
    @JoinTable(name = "genre2book")
    private List<Genre> genre;

    @ManyToMany(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinTable(name = "book2user")
    private List<User> user;

    @OneToMany(mappedBy = "book")
    private List<FileDownload> download;
}

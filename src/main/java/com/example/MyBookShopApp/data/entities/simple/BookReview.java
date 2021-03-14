package com.example.MyBookShopApp.data.entities.simple;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "book_review")
public class BookReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT NOT NULL")
    private Integer id;

    @Column(columnDefinition = "DATE NOT NULL")
    private LocalDate time;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String text;

    @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY)
    private Book book;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;
}

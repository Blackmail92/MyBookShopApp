package com.example.MyBookShopApp.data.entities.simple;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "review_like")
public class BookReviewLike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT NOT NULL")
    private Integer id;

    @Column(columnDefinition = "DATE NOT NULL")
    private LocalDateTime time;

    @Column(columnDefinition = "SMALLINT NOT NULL")
    private short value;

    @OneToOne(targetEntity = BookReview.class, fetch = FetchType.LAZY)
    private BookReview review;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;
}

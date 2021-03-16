package com.example.MyBookShopApp.data.entities.simple;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "book_file")
public class BookFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String hash;

    @OneToOne(targetEntity = BookFileType.class, fetch = FetchType.LAZY)
    private BookFileType type;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String path;
}

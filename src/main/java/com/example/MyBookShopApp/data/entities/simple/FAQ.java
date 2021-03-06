package com.example.MyBookShopApp.data.entities.simple;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "faq")
public class FAQ {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(columnDefinition = "INT NOT NULL DEFAULT 0")
    private Integer sortIndex;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String question;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String answer;
}

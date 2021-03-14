package com.example.MyBookShopApp.data.entities.simple;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user_contact")
public class UserContact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT NOT NULL")
    private Integer id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;

    @Column(columnDefinition = "SMALLINT NOT NULL")
    private Type type;

    @Column(columnDefinition = "BOOLEAN NOT NULL")
    private boolean approved;

    @Column(columnDefinition = "VARCHAR(255)")
    private String code;

    @Column(columnDefinition = "INT")
    private Integer codeTrials;

    @Column(columnDefinition = "DATE")
    private LocalDateTime codeTime;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String contact;

    enum Type {
        PHONE, EMAIL
    }
}

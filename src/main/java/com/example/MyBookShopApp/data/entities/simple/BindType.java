package com.example.MyBookShopApp.data.entities.simple;

import com.example.MyBookShopApp.data.entities.utils.TableEnums;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "bind_type")
public class BindType {
    @Id
    @Column(columnDefinition = "INT NOT NULL")
    @Basic
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @Transient
    private TableEnums.BookType typeEnum;

    @PostLoad
    void fillEnum() {
        this.typeEnum = TableEnums.BookType.of(id);
        this.name = typeEnum.name();
    }

    @PrePersist
    void fillVal() {
        this.id = typeEnum.getCode();
        this.name = typeEnum.name();
    }
}

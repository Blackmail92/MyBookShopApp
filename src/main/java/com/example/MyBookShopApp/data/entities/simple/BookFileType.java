package com.example.MyBookShopApp.data.entities.simple;

import com.example.MyBookShopApp.data.entities.utils.TableEnums;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "bookfile_type")
public class BookFileType {
    @Id
    @Basic
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String description;
    @Transient
    private TableEnums.FileType typeEnum;

    @OneToOne(mappedBy = "type")
    private BookFile file;

    @PostLoad
    void fillTransient() {
        this.typeEnum = TableEnums.FileType.of(name);
    }

    @PrePersist
    void fillPersistent() {
        if (name != null) {
            this.name = typeEnum.name();
            this.id = typeEnum.ordinal();
        }
    }
}

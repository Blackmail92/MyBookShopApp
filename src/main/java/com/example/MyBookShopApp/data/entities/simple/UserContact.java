package com.example.MyBookShopApp.data.entities.simple;

import com.example.MyBookShopApp.data.entities.utils.TableEnums;
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

    @Basic
    private String type;

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

    @Transient
    private TableEnums.ContactType typeEnum;

    @PostLoad
    void fillTransient() {
        this.typeEnum = TableEnums.ContactType.of(type);
    }

    @PrePersist
    void fillPersistent() {
        if (type != null) {
            this.type = typeEnum.name();
        }
    }
}

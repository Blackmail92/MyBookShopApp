package com.example.MyBookShopApp.data.repos;

import com.example.MyBookShopApp.data.entities.simple.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Integer> {
    Author findAuthorById(Integer id);
}

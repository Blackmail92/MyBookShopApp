package com.example.MyBookShopApp.data.repos;

import com.example.MyBookShopApp.data.entities.simple.Author;
import com.example.MyBookShopApp.data.entities.simple.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface AuthorRepo extends JpaRepository<Author, Integer> {
    Author findAuthorById(Integer id);
    Author findAuthorByBook(Book book);
    Author findFirstByName(String name);
}

package com.example.MyBookShopApp.data.repos;

import com.example.MyBookShopApp.data.entities.simple.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Integer> {

}

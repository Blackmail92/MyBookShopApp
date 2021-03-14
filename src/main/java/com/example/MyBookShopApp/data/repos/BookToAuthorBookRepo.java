package com.example.MyBookShopApp.data.repos;

import com.example.MyBookShopApp.data.entities.simple.Author;
import com.example.MyBookShopApp.data.entities.simple.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookToAuthorBookRepo extends JpaRepository<Book, Integer> {
    @Query("SELECT book FROM BookToAuthor WHERE author_id=:author")
    List<Book> findAllByAuthor(@Param("author") Author author);
}

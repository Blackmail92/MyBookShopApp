package com.example.MyBookShopApp.data.repos;

import com.example.MyBookShopApp.data.entities.simple.Author;
import com.example.MyBookShopApp.data.entities.simple.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BookToAuthorAuthorRepo extends JpaRepository<Author, Integer> {
    @Query("select b.author from BookToAuthor b where b.author.id=:id")
    Author findAuthorById(@Param("id") Integer id);

    @Query("select b.author from BookToAuthor b where b.book.id=:book")
    Author findAuthorByBook(@Param("book") Integer book);

    Author findAuthorByBook(Book b);
}

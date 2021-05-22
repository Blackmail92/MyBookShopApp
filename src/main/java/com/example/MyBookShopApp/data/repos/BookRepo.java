package com.example.MyBookShopApp.data.repos;

import com.example.MyBookShopApp.data.entities.simple.Author;
import com.example.MyBookShopApp.data.entities.simple.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BookRepo extends JpaRepository<Book, Integer> {
    List<Book> findAllByAuthor(Author author);
    List<Book> findByPubDateAfter(LocalDateTime date, Pageable page);
    List<Book> findAllByPubDateAfter(LocalDateTime date);
    List<Book> findAllByIsBestsellerIsTrue(Pageable page);
    List<Book> findBooksByIsBestsellerIsTrueAndPubDateAfter(LocalDateTime t, Pageable page);

    Book findBookBySlug(String slug);
    List<Book> findBooksByTitleContaining(String bookTitle, Pageable page);
    List<Book> findBooksByGenreId(Integer id, Pageable page);
    List<Book> findBooksByGenreId(Integer id);
}

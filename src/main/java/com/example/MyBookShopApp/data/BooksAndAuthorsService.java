package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.entities.simple.Author;
import com.example.MyBookShopApp.data.entities.simple.Book;
import com.example.MyBookShopApp.data.repos.AuthorRepo;
import com.example.MyBookShopApp.data.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BooksAndAuthorsService {
    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;
    private final List<BookAndAuthorDto> allBooksAndAuthors;

    @Autowired
    public BooksAndAuthorsService(
            BookRepo bookRepo, AuthorRepo authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        allBooksAndAuthors = getAllBooksAndAuthors();
    }

    public List<BookAndAuthorDto> getAllBooksAndAuthors() {
        List<Book> books = bookRepo.findAll();
        return createDto(books);
    }

    public List<BookAndAuthorDto> getRecentBooksAndAuthors() {
        LocalDateTime dateTo = LocalDateTime.now().minusMonths(1);
        List<Book> books = bookRepo.findAll()
                .stream()
                .filter(b -> b.getPubDate().isAfter(dateTo)).collect(Collectors.toList());
        return createDto(books);
    }

    public BookAndAuthorDto getBookBySlug(String slug) {
        return allBooksAndAuthors.stream()
                .filter(dto -> dto.getBook().getSlug().equals(slug))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public Map<String, List<Author>> getAllAuthorsGrouped() {
        List<Author> authors = authorRepo.findAll();
        return authors.stream().collect(Collectors.groupingBy(a -> a.getName().substring(0, 1)));
    }

    public Author getAuthorById(Integer id) {
        return authorRepo.findAuthorById(id);
    }

    public List<BookAndAuthorDto> getBooksByAuthor(Author author) {
        List<Book> books = bookRepo.findAllByAuthor(author);
        return createDto(books);
    }

    public List<BookAndAuthorDto> getRecommendedBooksAndAuthors() {
        List<Book> books = bookRepo.findAll()
                .stream()
                .filter(b -> b.getDiscount() != 0 || b.isBestseller())
                .collect(Collectors.toList());
        return createDto(books);
    }

    private List<BookAndAuthorDto> createDto(List<Book> books) {
        List<BookAndAuthorDto> booksAndAuthors = new ArrayList<>();
        // Запрос сделать через where author.id in ...
        books.forEach(b -> booksAndAuthors.add(
                createDto(b, authorRepo.findAuthorByBook(b).get(0))));
        return booksAndAuthors;
    }

    private BookAndAuthorDto createDto(Book book, Author author) {
        return new BookAndAuthorDto(book, author);
    }

    public List<BookAndAuthorDto> getPopularBooksAndAuthors() {
        List<Book> books = bookRepo.findAll().stream()
                .filter(Book::isBestseller)
                .collect(Collectors.toList());
        return createDto(books);
    }
}

package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.entities.connections.BookToAuthor;
import com.example.MyBookShopApp.data.entities.simple.Author;
import com.example.MyBookShopApp.data.entities.simple.Book;
import com.example.MyBookShopApp.data.repos.AuthorRepo;
import com.example.MyBookShopApp.data.repos.BookRepo;
import com.example.MyBookShopApp.data.repos.BookToAuthorAuthorRepo;
import com.example.MyBookShopApp.data.repos.BookToAuthorBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BooksAndAuthorsService {
    private final BookToAuthorBookRepo btaBookRepo;
    private final BookToAuthorAuthorRepo btaAuthorRepo;
    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;
    private final List<BookAndAuthorDto> allBooksAndAuthors;
    private BookAndAuthorDto book;

    @Autowired
    public BooksAndAuthorsService(
            BookToAuthorBookRepo btaBookRepo, BookToAuthorAuthorRepo btaAuthorRepo,
            BookRepo bookRepo, AuthorRepo authorRepo) {
        this.btaBookRepo = btaBookRepo;
        this.btaAuthorRepo = btaAuthorRepo;
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        allBooksAndAuthors = getAllBooksAndAuthors();
    }

    public List<BookAndAuthorDto> getAllBooksAndAuthors() {
        List<Book> books = bookRepo.findAll();
        return createDto(books);
    }

    public List<BookAndAuthorDto> getRecentBooksAndAuthors() {
        LocalDate dateTo = LocalDate.now().minusMonths(1);
        List<Book> books = bookRepo.findAll()
                .stream()
                .filter(b -> b.getPubDate().isBefore(dateTo)).collect(Collectors.toList());
        return createDto(books);
    }

    public BookAndAuthorDto getBookBySlug(String slug) {
        book = null;
        allBooksAndAuthors.forEach(dto -> book = dto.getBook().getSlug().equals(slug) ? dto : book);
        return book;
    }

    public Map<String, List<Author>> getAllAuthorsGrouped() {
        List<Author> authors = authorRepo.findAll();
        return authors.stream().collect(Collectors.groupingBy(a -> a.getName().substring(0, 1)));
    }

    public Author getAuthorById(Integer id) {
        return authorRepo.findAuthorById(id);
    }

    public List<BookAndAuthorDto> getBooksByAuthor(Author author) {
        List<Book> books = btaBookRepo.findAllByAuthor(author);
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
                new BookAndAuthorDto(b, btaAuthorRepo.findAuthorByBook(b.getId()))));
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

package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.entities.simple.Author;
import com.example.MyBookShopApp.data.entities.simple.Book;
import com.example.MyBookShopApp.data.repos.AuthorRepo;
import com.example.MyBookShopApp.data.repos.BookRepo;
import com.example.MyBookShopApp.errors.BookStoreApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BooksAndAuthorsService {
    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;

    @Autowired
    public BooksAndAuthorsService(
            BookRepo bookRepo, AuthorRepo authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    public List<BookDto> getAllBooksAndAuthors() {
        return BookMapper.fromList(bookRepo.findAll());
    }

    public BookDto getBookDtoBySlug(String slug) {
        return BookMapper.from(getBookBySlug(slug));
    }

    public Book getBookBySlug(String slug) {
        return bookRepo.findBookBySlug(slug);
    }

    public Map<String, List<Author>> getAllAuthorsGrouped() {
        List<Author> authors = authorRepo.findAll();
        return authors.stream().collect(Collectors.groupingBy(a -> a.getName().substring(0, 1)));
    }

    public Author getAuthorById(Integer id) {
        return authorRepo.findAuthorById(id);
    }

    public Author getAuthorByName(String name) {
        return authorRepo.findFirstByName(name);
    }

    public List<BookDto> getBooksByAuthor(Author author) {
        return BookMapper.fromList(bookRepo.findAllByAuthor(author));
    }

    public List<BookDto> getBooksByTitle(String title) throws BookStoreApiException {
        if (!StringUtils.hasText(title)) {
            throw new BookStoreApiException("Wrong param value passed");
        }
        List<Book> data = bookRepo.findBooksByTitleContaining(title);
        if (data.size() > 0) {
            return BookMapper.fromList(data);
        } else {
            throw new BookStoreApiException("No data found");
        }
    }

    public List<BookDto> getBooksByDate(Date from, Date till) {
        LocalDate fromDate;
        LocalDate tillDate;
        if (StringUtils.isEmpty(from)) {
            return getRecentBooksAndAuthors();
        }
        fromDate = from.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        tillDate = till.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return BookMapper.fromList(bookRepo.findAll().stream()
                .filter(book -> book.getPubDate().toLocalDate().isAfter(fromDate) && book.getPubDate().toLocalDate().isBefore(tillDate))
                .collect(Collectors.toList()));
    }

    public List<BookDto> getPopularBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        List<Book> books = bookRepo.findAllByIsBestsellerIsTrue(nextPage);
        return BookMapper.fromList(books);
    }

    public List<BookDto> getRecommendedBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        List<Book> books = bookRepo.findBooksByIsBestsellerIsTrueAndPubDateAfter(LocalDateTime.now().minusYears(1), nextPage);
        return BookMapper.fromList(books);
    }

    public List<BookDto> getBestsellers() {
        return BookMapper.fromList(bookRepo.findAllByIsBestsellerIsTrue());
    }

    public List<BookDto> getRecentBooksAndAuthors() {
        return BookMapper.fromList(bookRepo.findAllByPubDateAfter(LocalDateTime.now().minusYears(1)));
    }

    public List<BookDto> getRecentBooksAndAuthors(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return BookMapper.fromList(bookRepo.findByPubDateAfter(LocalDateTime.now().minusYears(1), nextPage));
    }

    public List<BookDto> getSearchResultBooks(String search, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        List<Book> books = bookRepo.findBooksByTitleContaining(search, nextPage);
        return BookMapper.fromList(books);
    }

    public List<BookDto> getBooksByGenre(Integer id, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        List<Book> books = bookRepo.findBooksByGenreId(id, nextPage);
        return BookMapper.fromList(books);
    }

    public List<BookDto> getBooksByGenre(Integer id) {
        List<Book> books = bookRepo.findBooksByGenreId(id);
        return BookMapper.fromList(books);
    }

    public BookRepo getBookRepo() {
        return bookRepo;
    }

    public List<BookDto> getBooksBySlugIn(List<String> slugs) {
        return BookMapper.fromList(bookRepo.findBooksBySlugIn(slugs));
    }
}

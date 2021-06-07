package com.example.MyBookShopApp.data.repos;

import com.example.MyBookShopApp.data.entities.simple.BookFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookFileRepo extends JpaRepository<BookFile, Integer> {
    BookFile findBookFileByHash(String hash);
}

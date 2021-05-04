package com.example.MyBookShopApp.data.repos;

import com.example.MyBookShopApp.data.entities.simple.Genre;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GenresRepo extends JpaRepository<Genre, Integer> {
    Genre getById(Integer id);
}

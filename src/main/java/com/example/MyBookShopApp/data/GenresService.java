package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.repos.GenresRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GenresService {
    private final GenresRepo repo;

    @Autowired
    public GenresService(GenresRepo repo) {
        this.repo = repo;
    }

    public List<GenreDto> getAllGenres() {
        return GenreMapper.fromList(repo.findAll());
    }

    public GenreDto getById(Integer id) {
        return GenreMapper.from(repo.getById(id));
    }
}

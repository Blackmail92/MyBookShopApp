package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.entities.simple.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreMapper {
    private static BooksAndAuthorsService booksService;

    @Autowired
    public GenreMapper (BooksAndAuthorsService booksService) {
        GenreMapper.booksService = booksService;
    }

    public static GenreDto from(Genre genre) {
        return GenreDto.builder()
                .id(genre.getId())
                .name(genre.getName())
                .booksCount(booksService.getBooksByGenre(genre.getId()).size())
                .build();
    }

    public static List<GenreDto> fromList(List<Genre> list) {
        return list.stream()
                .map(GenreMapper::from)
                .collect(Collectors.toList());
    }
}

package com.bookstore.book.controllers;

import com.bookstore.book.dtos.AuthorDto;
import com.bookstore.book.entities.Author;
import com.bookstore.book.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorConroller {

    private final AuthorService authorService;

    public AuthorConroller(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("api/v1/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAuthor(
            @RequestBody AuthorDto authDto
    ){
        return authorService.addAuthor(authDto);
    }

    @GetMapping("/api/v1/authors")
    public List<Author> getAuthors(){
        return authorService.getAuthors();
    }
}

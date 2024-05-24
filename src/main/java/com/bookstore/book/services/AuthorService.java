package com.bookstore.book.services;

import com.bookstore.book.dtos.AuthorDto;
import com.bookstore.book.entities.Author;
import com.bookstore.book.mappers.AuthorMapper;
import com.bookstore.book.repositories.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }


    public Author addAuthor(
            @RequestBody AuthorDto authDto
    ){
        Author author = authorMapper.toAuthor(authDto);
        return authorRepository.save(author);
    }

    public List<Author> getAuthors(){
        return  authorRepository.findAll();
    }
}

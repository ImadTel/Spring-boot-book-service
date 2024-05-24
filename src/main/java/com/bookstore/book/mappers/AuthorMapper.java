package com.bookstore.book.mappers;

import com.bookstore.book.dtos.AuthorDto;
import com.bookstore.book.entities.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public Author toAuthor(AuthorDto authDto){
        return  new  Author(
                null,
                authDto.name(),
                authDto.email(),
                authDto.date_of_birth(),
                null
        );
    }
}

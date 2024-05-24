package com.bookstore.book.mappers;

import com.bookstore.book.dtos.BookDto;
import com.bookstore.book.entities.Author;
import com.bookstore.book.entities.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toBook(BookDto bookDto){
        Author auth = new Author();
        auth.setId(bookDto.author_id());
        return  new  Book(
                null,
                bookDto.title(),
                null,
                bookDto.edition(),
                auth
        );
    }

    public BookDto toBookDto(Book book) {
        return new BookDto(
                book.getTitle(),
                book.getEdition(),
                book.getAuthor().getId()
        );
    }
}

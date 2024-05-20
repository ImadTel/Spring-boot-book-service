package com.bookstore.book.controllers;

import com.bookstore.book.entities.Book;
import com.bookstore.book.repositories.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FirstController {

    private final BookRepository bookRepository;

    public FirstController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/api/v1/hello")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String SayHello(){
        return  "Hello world";
    }

    @GetMapping("/api/v1/books")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getBooks(){
        return bookRepository.findAll() ;
    }

    @PostMapping("/api/v1/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @GetMapping("/api/v1/books/search/{book-title}")
    public List<Book> filterBooks(
            @PathVariable("book-title") String title
    ){
        return bookRepository.findAllByTitleContaining(title);
    }


}

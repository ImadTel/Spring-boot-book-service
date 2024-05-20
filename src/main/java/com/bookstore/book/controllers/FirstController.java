package com.bookstore.book.controllers;

import com.bookstore.book.entities.Author;
import com.bookstore.book.entities.Book;
import com.bookstore.book.repositories.AuthorRepository;
import com.bookstore.book.repositories.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FirstController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public FirstController(BookRepository bookRepository,AuthorRepository authorRepository ) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/api/v1/hello")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String SayHello(){
        return  "Hello world";
    }

    @PostMapping("api/v1/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAuthor(
           @RequestBody Author author
    ){
        return authorRepository.save(author);
    }

    @GetMapping("/api/v1/authors")
    public List<Author> getAuthors(){
        return  authorRepository.findAll();
    }

    @GetMapping("/api/v1/books")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getBooks(){
        return bookRepository.findAll() ;
    }

    @PostMapping("/api/v1/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody  Book book) {
        return bookRepository.save(book);
    }

    @GetMapping("/api/v1/books/search/{book-title}")
    public List<Book> filterBooks(
            @PathVariable("book-title") String title
    ){
        return bookRepository.findAllByTitleContaining(title);
    }

    @DeleteMapping("api/v1/books/{book-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(
            @PathVariable("book-id") Long id
    ){
        bookRepository.deleteById(id);
    }

}

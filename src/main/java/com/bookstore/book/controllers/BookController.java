package com.bookstore.book.controllers;

import com.bookstore.book.dtos.BookDto;
import com.bookstore.book.dtos.BookResponseDto;
import com.bookstore.book.entities.Book;
import com.bookstore.book.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {
    private final BookService bookService;
    
    public  BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/api/v1/books")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBooks(){
        return bookService.getBooks();
    }


    @PostMapping("/api/v1/books")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDto addBook(@RequestBody BookDto bookDto) {;
        return  bookService.addBook(bookDto);
    }

    @GetMapping("/api/v1/books/search/{book-title}")
    public List<Book> filterBooks(
            @PathVariable("book-title") String title
    ){
        return bookService.filterBooks(title);
    }

    @DeleteMapping("api/v1/books/{book-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(
            @PathVariable("book-id") Long id
    ){
        bookService.deleteBook(id);
    }
}

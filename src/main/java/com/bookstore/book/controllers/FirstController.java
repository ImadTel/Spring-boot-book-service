package com.bookstore.book.controllers;

import com.bookstore.book.dtos.AuthorDto;
import com.bookstore.book.dtos.BookDto;
import com.bookstore.book.dtos.BookResponseDto;
import com.bookstore.book.entities.Author;
import com.bookstore.book.entities.Book;
import com.bookstore.book.repositories.AuthorRepository;
import com.bookstore.book.repositories.BookRepository;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    public static Author toAuthor(AuthorDto authDto){
        return  new  Author(
                null,
                authDto.name(),
                authDto.email(),
                authDto.date_of_birth(),
                null
        );
    }

    public static Book toBook(BookDto bookDto){
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

    @PostMapping("api/v1/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAuthor(
           @RequestBody AuthorDto authDto
    ){
        Author author = toAuthor(authDto);
        return authorRepository.save(author);
    }

    @GetMapping("/api/v1/authors")
    public List<Author> getAuthors(){
        return  authorRepository.findAll();
    }

    private BookDto toBookDto(Book book) {
        return new BookDto(
                book.getTitle(),
                book.getEdition(),
                book.getAuthor().getId()
        );
    }

    @GetMapping("/api/v1/books")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBooks(){
        return bookRepository.findAll().stream()
                .map(this::toBookDto)
                .collect(Collectors.toList());
    }



    @PostMapping("/api/v1/books")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDto addBook(@RequestBody  BookDto bookDto) {
        Book book = bookRepository.save(toBook(bookDto));
        Integer auth_id = Math.toIntExact(book.getAuthor().getId());
        return  new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getEdition(),
                auth_id
        );
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

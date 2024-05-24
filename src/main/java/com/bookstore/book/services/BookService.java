package com.bookstore.book.services;

import com.bookstore.book.dtos.BookDto;
import com.bookstore.book.dtos.BookResponseDto;
import com.bookstore.book.entities.Book;
import com.bookstore.book.mappers.BookMapper;
import com.bookstore.book.repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    public BookResponseDto addBook(BookDto bookDto) {
        Book book = bookRepository.save(bookMapper.toBook(bookDto));
        Integer auth_id = Math.toIntExact(book.getAuthor().getId());
        return  new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getEdition(),
                auth_id
        );
    }

    public List<Book> filterBooks(String title){
        return bookRepository.findAllByTitleContaining(title);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

    public List<BookDto> getBooks(){
        return bookRepository.findAll().stream()
                .map(bookMapper::toBookDto)
                .collect(Collectors.toList());
    }
}

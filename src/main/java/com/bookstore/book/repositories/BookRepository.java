package com.bookstore.book.repositories;

import com.bookstore.book.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
     List<Book> findAllByTitleContaining(String p);
}

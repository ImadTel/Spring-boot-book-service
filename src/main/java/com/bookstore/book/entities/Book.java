package com.bookstore.book.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            allocationSize = 1,
            sequenceName = "book_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private Long id;
    @Column(unique = true)
    private String title;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDate published_in;
    private Integer edition;

    @ManyToOne
    @JoinColumn(
            name = "author_id"
    )
    @JsonBackReference
    private Author author;
}

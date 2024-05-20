package com.bookstore.book.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
public class Author {
    @Id
    @SequenceGenerator(
            name = "author_sequence",
            allocationSize = 1,
            sequenceName = "author_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private LocalDate date_of_birth;
}

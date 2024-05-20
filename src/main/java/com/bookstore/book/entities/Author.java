package com.bookstore.book.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(
            mappedBy = "author"
    )
    @JsonManagedReference
    private List<Book> books;
}

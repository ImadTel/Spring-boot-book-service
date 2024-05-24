package com.bookstore.book.dtos;

import lombok.Data;

import java.time.LocalDate;

public record AuthorDto(
    String name,
    String email,
    LocalDate date_of_birth
            ){}


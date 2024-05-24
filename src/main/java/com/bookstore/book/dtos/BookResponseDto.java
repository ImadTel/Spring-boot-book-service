package com.bookstore.book.dtos;

public record BookResponseDto(
        Long id,
        String title,
        Integer edition,
        Integer author_id

) {
}

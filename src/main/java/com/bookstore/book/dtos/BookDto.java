package com.bookstore.book.dtos;

import lombok.NonNull;

public record BookDto(
        String title,
        Integer edition,
        Long author_id
) {
}

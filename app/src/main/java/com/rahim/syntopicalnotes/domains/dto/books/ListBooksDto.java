package com.rahim.syntopicalnotes.domains.dto.books;

import java.time.LocalDate;


public record ListBooksDto (
    Long id,
    String name,
    String isbn,
    LocalDate datePublished,
    String authorName
){}

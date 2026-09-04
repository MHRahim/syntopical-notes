
package com.rahim.syntopicalnotes.domains.dto.books;

import java.time.LocalDate;

import com.rahim.syntopicalnotes.domains.dto.authors.AuthorDto;


public record BookDto (
    Long id,
    String name,
    String isbn,
    LocalDate datePublished,
    AuthorDto author
){}

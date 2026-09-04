package com.rahim.syntopicalnotes.domains.dto.authors;

import java.time.LocalDate;

public record AuthorDto(
        Long id,
        String name,
        LocalDate dateOfBirth,
        LocalDate dateOfDeath
        ) {
}

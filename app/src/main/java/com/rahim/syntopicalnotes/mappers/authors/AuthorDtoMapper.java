package com.rahim.syntopicalnotes.mappers.authors;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rahim.syntopicalnotes.domains.dto.authors.AuthorDto;
import com.rahim.syntopicalnotes.domains.entity.Author;
import com.rahim.syntopicalnotes.mappers.Mapper;

@Component
public class AuthorDtoMapper implements Mapper<AuthorDto, Author> {

    @Override
    public AuthorDto mapFrom(Author a) {
        return new AuthorDto(
            a.getId(),
            a.getName(),
            a.getDateOfBirth(),
            a.getDateOfDeath()
        );
    }

    @Override
    public List<AuthorDto> mapFromMany(List<Author> authors) {
        return authors.stream().map(this::mapFrom).toList();
    }

    @Override
    public Author mapTo(AuthorDto a) {
        return null;
    }
}

package com.rahim.syntopicalnotes.services.authors;


import java.util.List;

import org.springframework.stereotype.Service;

import com.rahim.syntopicalnotes.domains.dto.authors.AuthorDto;
import com.rahim.syntopicalnotes.domains.entity.Author;
import com.rahim.syntopicalnotes.exceptions.ResourceNotFoundException;
import com.rahim.syntopicalnotes.mappers.authors.AuthorDtoMapper;
import com.rahim.syntopicalnotes.repositories.AuthorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository repo;
    private final AuthorDtoMapper mapper;

    public List<AuthorDto> getAllAuthors() {
        List<Author> authorEntities = this.repo.findAll();
        List<AuthorDto> authorDtos = this.mapper.mapFromMany(authorEntities);
        return authorDtos;
    } 

    public AuthorDto getAuthorById(Long id) {
        Author author = this.repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Author with an id of: " + id + " is not found"));
        AuthorDto authorDto = this.mapper.mapFrom(author);

        return authorDto;
    }
}

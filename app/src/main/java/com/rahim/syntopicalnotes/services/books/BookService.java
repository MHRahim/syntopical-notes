package com.rahim.syntopicalnotes.services.books;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rahim.syntopicalnotes.domains.dto.books.BookDto;
import com.rahim.syntopicalnotes.domains.dto.books.ListBooksDto;
import com.rahim.syntopicalnotes.domains.entity.Book;
import com.rahim.syntopicalnotes.exceptions.ResourceNotFoundException;
import com.rahim.syntopicalnotes.mappers.books.BookDtoMapper;
import com.rahim.syntopicalnotes.mappers.books.ListBooksMapper;
import com.rahim.syntopicalnotes.repositories.BookRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookService {

    private final BookRepository repo;
    private final ListBooksMapper listBookMapper;
    private final BookDtoMapper bookDtoMapper;


    public List<ListBooksDto> findAllBooks() {
        List<Book> bookEntities = repo.findAllWithAuthor();
        List<ListBooksDto> bookDtos = listBookMapper.mapFromMany(bookEntities);
        return bookDtos;
    }

    @Transactional(readOnly = true)
    public BookDto getBookById(Long id) {
        return this.repo.findById(id).
            map(bookDtoMapper::mapFrom)
            .orElseThrow(() -> new ResourceNotFoundException("Book with the id of " + id + " is not found")); 
    }
}

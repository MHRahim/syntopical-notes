package com.rahim.syntopicalnotes.mappers.books;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rahim.syntopicalnotes.domains.dto.books.BookDto;
import com.rahim.syntopicalnotes.domains.entity.Book;
import com.rahim.syntopicalnotes.mappers.Mapper;
import com.rahim.syntopicalnotes.mappers.authors.AuthorMapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BookDtoMapper implements Mapper<BookDto, Book> {

    private final AuthorMapper authorMapper;

    @Override
    public BookDto mapFrom(Book b) {
        return new BookDto(
            b.getId(),
            b.getName(),
            b.getIsbn(),
            b.getDatePublished(),
            authorMapper.mapFrom(b.getAuthor())
        );
    }

    @Override
    public List<BookDto> mapFromMany(List<Book> books) {
        List<BookDto> list = new ArrayList<>();
        for (Book book : books) {
            list.add(this.mapFrom(book));
        }
        return list;
    }

    @Override
    public Book mapTo(BookDto a) {
        return null;
    }
}

package com.rahim.syntopicalnotes.mappers.books;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rahim.syntopicalnotes.domains.dto.books.ListBooksDto;
import com.rahim.syntopicalnotes.domains.entity.Book;
import com.rahim.syntopicalnotes.mappers.Mapper;

@Component
public class ListBooksMapper implements Mapper<ListBooksDto, Book> {

	@Override
	public ListBooksDto mapFrom(Book b) {

		return new ListBooksDto(b.getId(),
                b.getName(), 
                b.getIsbn(), 
                b.getDatePublished(), 
                b.getAuthor().getName());
	}

	@Override
	public List<ListBooksDto> mapFromMany(List<Book> b) {
        List<ListBooksDto> lb = new ArrayList<>(); 

        for (Book data : b) {
            lb.add(this.mapFrom(data)); 
        }

		return lb;
	}

	@Override
	public Book mapTo(ListBooksDto a) {
		return null;
	}

    
}

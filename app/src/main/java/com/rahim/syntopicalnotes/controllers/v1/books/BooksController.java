package com.rahim.syntopicalnotes.controllers.v1.books;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahim.syntopicalnotes.domains.dto.books.BookDto;
import com.rahim.syntopicalnotes.domains.dto.books.ListBooksDto;
import com.rahim.syntopicalnotes.domains.entity.Book;
import com.rahim.syntopicalnotes.mappers.books.ListBooksMapper;
import com.rahim.syntopicalnotes.repositories.BookRepository;
import com.rahim.syntopicalnotes.services.books.BookService;
import com.rahim.syntopicalnotes.utils.ResponseFormatter;
import com.rahim.syntopicalnotes.utils.ResponseFormatter.Response;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BooksController {
    private final ResponseFormatter responseFormatter;
    private final BookService service;
    
    
    @GetMapping("")
    public ResponseEntity<?> listBooks() {
        List<ListBooksDto> bookList = this.service.findAllBooks();
        return responseFormatter.send(200, "ok.", bookList);
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        BookDto bookDto = this.service.getBookById(id);
        return responseFormatter.send(200, "ok.", bookDto);
    }



}

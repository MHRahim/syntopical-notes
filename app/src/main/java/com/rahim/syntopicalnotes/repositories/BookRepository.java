package com.rahim.syntopicalnotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rahim.syntopicalnotes.domains.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b JOIN FETCH b.author")
    List<Book> findAllWithAuthor();
}

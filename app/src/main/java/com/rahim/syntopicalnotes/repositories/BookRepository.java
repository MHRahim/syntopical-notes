package com.rahim.syntopicalnotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahim.syntopicalnotes.domains.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}

package com.rahim.syntopicalnotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rahim.syntopicalnotes.domains.entity.Author;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}

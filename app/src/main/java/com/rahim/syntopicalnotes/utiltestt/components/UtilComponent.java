package com.rahim.syntopicalnotes.utiltestt.components;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rahim.syntopicalnotes.domains.entity.Author;
import com.rahim.syntopicalnotes.domains.entity.Book;
import com.rahim.syntopicalnotes.repositories.AuthorRepository;


@Component
public class UtilComponent {

    private AuthorRepository authorRepo;

	public UtilComponent(AuthorRepository authorRepo) {
		this.authorRepo = authorRepo;
	}
    
    @Transactional(readOnly = true)
    public void display() {
        List<Author> list = this.authorRepo.findAll();

        for(Author a: list) {
            System.out.println(a.getName());
            System.out.println("-------------------------------");

            for(Book b : a.getBooks()){
                System.out.print("- ");
                System.out.println(b.getName());
            }

            System.out.println();
            System.out.println();
            System.out.println();
        }
    }
}

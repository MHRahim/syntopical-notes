package com.rahim.syntopicalnotes.seeding.seeders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rahim.syntopicalnotes.domains.entity.Author;
import com.rahim.syntopicalnotes.repositories.AuthorRepository;
import com.rahim.syntopicalnotes.seeding.Seeder;


@Component
public class AuthorSeeder implements Seeder {

    private List<Author> authorData;
    private AuthorRepository authorRepo;


	public AuthorSeeder(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    private void seedData() {
        List<Author> authorData = new ArrayList<>();

        authorData.add(new Author(
            null, 
            "Daniel Goleman", 
            LocalDate.of(1946, 3, 7), 
            null, 
            new ArrayList<>(), 
            "Stockton, California", 
            new Date(), 
            new Date()
        ));

        authorData.add(new Author(
            null, 
            "Daniel Kahneman", 
            LocalDate.of(1934, 3, 5), 
            LocalDate.of(2024, 3, 27), 
            new ArrayList<>(), 
            "Tel Aviv, Mandatory Palestine", 
            new Date(), 
            new Date()
        ));

        authorData.add(new Author(
            null, 
            "Jonathan Haidt", 
            LocalDate.of(1963, 10, 19), 
            null, 
            new ArrayList<>(), 
            "New York City, New York", 
            new Date(), 
            new Date()
        ));

        authorData.add(new Author(
            null, 
            "Nassim Nicholas Taleb", 
            LocalDate.of(1960, 1, 1), 
            null, 
            new ArrayList<>(), 
            "Amioun, Lebanon", 
            new Date(), 
            new Date()
        ));

        authorData.add(new Author(
            null, 
            "Adam Grant", 
            LocalDate.of(1981, 8, 13), 
            null, 
            new ArrayList<>(), 
            "West Bloomfield, Michigan", 
            new Date(), 
            new Date()
        ));

        this.authorData = authorData;
    }

    public void seed() {
        this.seedData();
        for (Author a : authorData) {
            this.authorRepo.saveAndFlush(a);
        }
    } 
}

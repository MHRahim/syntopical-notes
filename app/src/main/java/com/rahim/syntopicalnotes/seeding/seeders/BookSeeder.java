package com.rahim.syntopicalnotes.seeding.seeders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.rahim.syntopicalnotes.domains.entity.Author;
import com.rahim.syntopicalnotes.domains.entity.Book;
import com.rahim.syntopicalnotes.repositories.AuthorRepository;
import com.rahim.syntopicalnotes.repositories.BookRepository;
import com.rahim.syntopicalnotes.seeding.Seeder;

@Component
public class BookSeeder implements Seeder{

    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;
    private List<Book> bookData;

    public BookSeeder(BookRepository bookRepo, AuthorRepository authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        this.bookData = new ArrayList<Book>();
	}

    private void seedData() {
        List<Author> authors = new ArrayList<Author>();

        for(Long i = 0l; i < 5; i++) {
            this.authorRepo.findById(i + 1).ifPresent(a -> {
                authors.add(a);
            });
        }


        for (int i = 0; i < authors.size(); i++) {
            Author s = authors.get(i);
            Date now = new Date();

            if (s.getId() == 1l) {
                this.bookData.add(new Book(null, "Emotional Intelligence", 
                    "9780553383713", LocalDate.of(1995, 9, 27), s, now, now));
                this.bookData.add(new Book(null, "Social Intelligence", 
                    "9780553383714", LocalDate.of(2006, 9, 26), s, now, now));
                this.bookData.add(new Book(null, "Focus", 
                    "9780062114969", LocalDate.of(2013, 10, 8), s, now, now));
                this.bookData.add(new Book(null, "Working with Emotional Intelligence", 
                    "9780553378580", LocalDate.of(1998, 10, 20), s, now, now));
                this.bookData.add(new Book(null, "Altered Traits", 
                    "9780399184383", LocalDate.of(2017, 9, 5), s, now, now));
            }

            if (s.getId() == 2l) {
                this.bookData.add(new Book(null, "Thinking, Fast and Slow", 
                    "9780374275631", LocalDate.of(2011, 10, 25), s, now, now));
                this.bookData.add(new Book(null, "Attention and Effort", 
                    "9780130505187", LocalDate.of(1973, 1, 1), s, now, now));
                this.bookData.add(new Book(null, "Noise: A Flaw in Human Judgment", 
                    "9780316451406", LocalDate.of(2021, 5, 18), s, now, now));
                this.bookData.add(new Book(null, "Well-Being", 
                    "9780871544230", LocalDate.of(1999, 7, 8), s, now, now));
                this.bookData.add(new Book(null, "Choices, Values, and Frames", 
                    "9780521627498", LocalDate.of(2000, 9, 25), s, now, now));
            }

            if (s.getId() == 3l) {
                this.bookData.add(new Book(null, "The Happiness Hypothesis", 
                    "9780465028023", LocalDate.of(2006, 12, 1), s, now, now));
                this.bookData.add(new Book(null, "The Righteous Mind", 
                    "9780307377906", LocalDate.of(2012, 3, 13), s, now, now));
                this.bookData.add(new Book(null, "The Coddling of the American Mind", 
                    "9780735224896", LocalDate.of(2018, 9, 4), s, now, now));
                this.bookData.add(new Book(null, "The Anxious Generation", 
                    "9780593655030", LocalDate.of(2024, 3, 26), s, now, now));
                this.bookData.add(new Book(null, "Moral Child", 
                    "9780029135211", LocalDate.of(1988, 11, 1), s, now, now));
            }

            if (s.getId() == 4l) {
                this.bookData.add(new Book(null, "The Black Swan", 
                    "9781400063512", LocalDate.of(2007, 4, 17), s, now, now));
                this.bookData.add(new Book(null, "Antifragile", 
                    "9781400067824", LocalDate.of(2012, 11, 27), s, now, now));
                this.bookData.add(new Book(null, "Fooled by Randomness", 
                    "9780812975215", LocalDate.of(2001, 1, 1), s, now, now));
                this.bookData.add(new Book(null, "Skin in the Game", 
                    "9780425284643", LocalDate.of(2018, 2, 20), s, now, now));
                this.bookData.add(new Book(null, "The Bed of Procrustes", 
                    "9781400069972", LocalDate.of(2010, 11, 30), s, now, now));
            }

            if (s.getId() == 5l) {
                this.bookData.add(new Book(null, "Give and Take", 
                    "9780143124931", LocalDate.of(2013, 4, 9), s, now, now));
                this.bookData.add(new Book(null, "Originals", 
                    "9780143128854", LocalDate.of(2016, 2, 2), s, now, now));
                this.bookData.add(new Book(null, "Think Again", 
                    "9781984878106", LocalDate.of(2021, 2, 2), s, now, now));
                this.bookData.add(new Book(null, "Hidden Potential", 
                    "9780593653142", LocalDate.of(2023, 10, 24), s, now, now));
                this.bookData.add(new Book(null, "Option B", 
                    "9781524733148", LocalDate.of(2017, 4, 24), s, now, now));
            }
        }

    }


	public void seed() {
        this.seedData();
        for (Book b : this.bookData) {
           this.bookRepo.save(b);
        }
    }    
}

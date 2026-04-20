package com.rahim.syntopicalnotes.seeding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.rahim.syntopicalnotes")
@EnableJpaRepositories(basePackages = "com.rahim.syntopicalnotes.repositories")
@EntityScan(basePackages = "com.rahim.syntopicalnotes.domains")
@Profile("seed")
public class Seeding implements CommandLineRunner {

    private final Map<String, Seeder> seedersList;

    @Autowired
    public Seeding(@Qualifier("seedersList") Map<String, Seeder> sl) {
        this.seedersList = sl;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Seeding.class);

        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }


    public void run(String... args) throws IllegalArgumentException {
        System.out.println("Seeding the database......");
        List<Seeder> seedersToExecute = new ArrayList<Seeder>();
        
        for (String seederName : args) {
            Seeder selectedSeeder = this.seedersList.get(seederName);
            if (selectedSeeder != null ){
                seedersToExecute.add(selectedSeeder);
                continue;
            }

            throw new IllegalArgumentException("Unknown seeder name: " + seederName);
        }

        for (Seeder s: seedersToExecute) {
            s.seed();
        }
    }
}

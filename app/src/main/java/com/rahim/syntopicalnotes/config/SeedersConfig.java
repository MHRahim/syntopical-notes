package com.rahim.syntopicalnotes.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rahim.syntopicalnotes.seeding.Seeder;
import com.rahim.syntopicalnotes.seeding.seeders.AuthorSeeder;
import com.rahim.syntopicalnotes.seeding.seeders.BookSeeder;
import com.rahim.syntopicalnotes.seeding.seeders.UserSeeder;

@Configuration
public class SeedersConfig {
    
    @Bean
    Map<String, Seeder> seedersList(
        UserSeeder userSeeder,
        AuthorSeeder authorSeeder,
        BookSeeder bookSeeder
    ) {
        Map<String, Seeder> seeders = new HashMap<String, Seeder>();   

        seeders.put("UserSeeder", userSeeder);
        seeders.put("AuthorSeeder", authorSeeder);
        seeders.put("BookSeeder", bookSeeder);

        return seeders;
    }
}

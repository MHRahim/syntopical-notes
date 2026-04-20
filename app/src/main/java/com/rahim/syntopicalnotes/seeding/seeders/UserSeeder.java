package com.rahim.syntopicalnotes.seeding.seeders;

import org.springframework.stereotype.Component;

import com.rahim.syntopicalnotes.seeding.Seeder;


@Component
public class UserSeeder implements Seeder{

    @Override
    public void seed() {
        System.out.println("Seeding users");
    }
}

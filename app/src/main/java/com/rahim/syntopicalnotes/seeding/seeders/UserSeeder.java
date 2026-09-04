package com.rahim.syntopicalnotes.seeding.seeders;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rahim.syntopicalnotes.domains.dto.auth.CreateUser;
import com.rahim.syntopicalnotes.domains.entity.User;
import com.rahim.syntopicalnotes.mappers.auth.CreateUserMapper;
import com.rahim.syntopicalnotes.repositories.UserRepository;
import com.rahim.syntopicalnotes.seeding.Seeder;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class UserSeeder implements Seeder{

    private final UserRepository repo;
    private final CreateUserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    private List<CreateUser> users = List.of(
        new CreateUser("test2@gmail.com", "admin", "Thepassword", 1L),
        new CreateUser("test3@gmail.com", "member", "Thepassword", 2L)
    );

    @Override
    public void seed() {
        System.out.println("Seeding users...");
        System.out.println(this.users.size());
        for (CreateUser createUser : this.users) {
                System.out.println("Creating user: " + createUser.getEmail());

                createUser.setPassword(passwordEncoder.encode(createUser.getPassword()));
                User user = mapper.mapTo(createUser);

                this.repo.save(user);
        }
    }
}

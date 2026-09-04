package com.rahim.syntopicalnotes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahim.syntopicalnotes.domains.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

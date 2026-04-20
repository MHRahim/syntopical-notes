package com.rahim.syntopicalnotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahim.syntopicalnotes.domains.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

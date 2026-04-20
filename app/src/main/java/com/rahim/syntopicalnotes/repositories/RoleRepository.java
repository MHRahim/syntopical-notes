package com.rahim.syntopicalnotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahim.syntopicalnotes.domains.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

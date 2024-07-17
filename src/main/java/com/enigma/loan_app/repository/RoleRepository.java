package com.enigma.loan_app.repository;

import com.enigma.loan_app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(Role.ERole role);
}


package com.enigma.loan_app.repository;

import com.enigma.loan_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

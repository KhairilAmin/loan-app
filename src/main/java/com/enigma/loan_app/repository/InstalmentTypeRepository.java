package com.enigma.loan_app.repository;

import com.enigma.loan_app.entity.InstalmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstalmentTypeRepository extends JpaRepository<InstalmentType, String> {
}

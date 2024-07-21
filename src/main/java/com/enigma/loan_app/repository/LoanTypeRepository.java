package com.enigma.loan_app.repository;

import com.enigma.loan_app.entity.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTypeRepository extends JpaRepository<LoanType, String> {
}

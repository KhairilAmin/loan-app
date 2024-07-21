package com.enigma.loan_app.repository;

import com.enigma.loan_app.entity.LoanTransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanDetailRepository extends JpaRepository<LoanTransactionDetail, String> {

}

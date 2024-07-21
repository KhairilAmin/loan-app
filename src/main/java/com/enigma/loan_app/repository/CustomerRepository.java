package com.enigma.loan_app.repository;

import com.enigma.loan_app.entity.Customer;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    List<Customer> findAllByDeletedFalse();
    Customer findByIdAndDeletedFalse(String id);
}

package com.enigma.loan_app.service;

import com.enigma.loan_app.dto.request.LoanTypeRequest;
import com.enigma.loan_app.dto.response.LoanTypeResponse;

import java.util.List;

public interface LoanTypeService {
    List<LoanTypeResponse> findAll();
    LoanTypeResponse findById(String id);
    LoanTypeResponse update(LoanTypeRequest loanTypeRequest);
    LoanTypeResponse save(LoanTypeRequest loanTypeRequest);
    void delete(String id);
}

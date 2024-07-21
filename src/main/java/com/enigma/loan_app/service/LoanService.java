package com.enigma.loan_app.service;

import com.enigma.loan_app.dto.request.ApproveLoanRequest;
import com.enigma.loan_app.dto.request.LoanRequest;
import com.enigma.loan_app.dto.response.LoanResponse;

public interface LoanService {
    LoanResponse createLoan(LoanRequest loanRequest);
    LoanResponse approveLoan(ApproveLoanRequest approveLoanRequest,String adminId);
    LoanResponse getLoanById(String loanId);
}

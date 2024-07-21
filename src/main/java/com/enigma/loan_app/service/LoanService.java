package com.enigma.loan_app.service;

import com.enigma.loan_app.dto.request.ApproveLoanRequest;
import com.enigma.loan_app.dto.request.LoanRequest;
import com.enigma.loan_app.dto.response.LoanResponse;
import com.enigma.loan_app.dto.response.PaymentResponse;
import com.enigma.loan_app.dto.response.ProfilePictureResponse;
import org.springframework.web.multipart.MultipartFile;

public interface LoanService {
    LoanResponse createLoan(LoanRequest loanRequest);
    LoanResponse approveLoan(ApproveLoanRequest approveLoanRequest,String adminId);
    LoanResponse getLoanById(String loanId);
    PaymentResponse paymentPicture(MultipartFile file, String id);
}

package com.enigma.loan_app.controller;

import com.enigma.loan_app.constant.APIUrl;
import com.enigma.loan_app.dto.request.ApproveLoanRequest;
import com.enigma.loan_app.dto.request.LoanRequest;
import com.enigma.loan_app.dto.response.CommonResponse;
import com.enigma.loan_app.dto.response.LoanResponse;
import com.enigma.loan_app.dto.response.PaymentResponse;
import com.enigma.loan_app.dto.response.ProfilePictureResponse;
import com.enigma.loan_app.service.LoanService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIUrl.TRANSACTION_API)
@SecurityRequirement(name = "Authorization")
public class LoanTransactionController {
    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<CommonResponse<LoanResponse>> createLoan(@RequestBody LoanRequest loanRequest) {
        LoanResponse loanResponse = loanService.createLoan(loanRequest);
        CommonResponse commonResponse = CommonResponse.<LoanResponse>builder()
                .message("Loan request successfully created")
                .data(Optional.of(loanResponse))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @PostMapping("/{adminId}/approve")
    public ResponseEntity<CommonResponse<LoanResponse>> approveLoan(@PathVariable String adminId, @RequestBody ApproveLoanRequest approveLoanRequest) {
        LoanResponse loanResponse = loanService.approveLoan(approveLoanRequest,adminId);
        CommonResponse commonResponse = CommonResponse.<LoanResponse>builder()
                .message("Loan request approved!")
                .data(Optional.of(loanResponse))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<CommonResponse<LoanResponse>> getLoan(@PathVariable String loanId) {
        LoanResponse loanResponse = loanService.getLoanById(loanId);
        CommonResponse commonResponse = CommonResponse.<LoanResponse>builder()
                .message("Loan request found!")
                .data(Optional.of(loanResponse))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<CommonResponse<PaymentResponse>> uploadAvatar(@PathVariable String id, @RequestParam("file") MultipartFile file) {
        PaymentResponse paymentResponse = loanService.paymentPicture(file,id);

        CommonResponse<PaymentResponse> commonResponse = CommonResponse.<PaymentResponse>builder()
                .message("Payment Successful!")
                .data(Optional.of(paymentResponse))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }
}

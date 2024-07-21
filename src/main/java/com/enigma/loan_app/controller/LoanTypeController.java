package com.enigma.loan_app.controller;

import com.enigma.loan_app.constant.APIUrl;
import com.enigma.loan_app.dto.request.LoanTypeRequest;
import com.enigma.loan_app.dto.response.CommonResponse;
import com.enigma.loan_app.dto.response.LoanTypeResponse;
import com.enigma.loan_app.entity.LoanType;
import com.enigma.loan_app.service.LoanTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIUrl.LOANTYPE_API)
@SecurityRequirement(name = "Authorization")
public class LoanTypeController {
    private final LoanTypeService loanTypeService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<LoanTypeResponse>>> getAllLoanTypes() {
        List<LoanTypeResponse> response = loanTypeService.findAll();
        CommonResponse<List<LoanTypeResponse>> commonResponse = CommonResponse.<List<LoanTypeResponse>>builder()
                .message("Data of Loan Type")
                .data(Optional.of(response))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<LoanTypeResponse>> getLoanTypeById(@PathVariable String id) {
        LoanTypeResponse loanTypeResponse = loanTypeService.findById(id);
        CommonResponse<LoanTypeResponse> commonResponse = CommonResponse.<LoanTypeResponse>builder()
                .message("Loan Type Found!")
                .data(Optional.of(loanTypeResponse))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @PostMapping
    public ResponseEntity<CommonResponse<LoanTypeResponse>> createLoanType(@RequestBody LoanTypeRequest loanTypeRequest) {
        LoanTypeResponse loanTypeResponse = loanTypeService.save(loanTypeRequest);
        CommonResponse<LoanTypeResponse> commonResponse = CommonResponse.<LoanTypeResponse>builder()
                .message("Loan Type Created!")
                .data(Optional.of(loanTypeResponse))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @PutMapping()
    public ResponseEntity<CommonResponse<LoanTypeResponse>> updateLoanType(@RequestBody LoanTypeRequest loanTypeRequest) {
        LoanTypeResponse loanTypeResponse = loanTypeService.update(loanTypeRequest);

        CommonResponse<LoanTypeResponse> commonResponse = CommonResponse.<LoanTypeResponse>builder()
                .message("Loan Type Updated!")
                .data(Optional.of(loanTypeResponse))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deleteLoanType(@PathVariable String id) {
        loanTypeService.delete(id);

        CommonResponse commonResponse = CommonResponse.builder()
                .message("Loan Type Deleted!")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }
}

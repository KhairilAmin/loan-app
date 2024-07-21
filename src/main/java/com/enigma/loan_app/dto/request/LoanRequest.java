package com.enigma.loan_app.dto.request;

import com.enigma.loan_app.entity.Customer;
import com.enigma.loan_app.entity.InstalmentType;
import com.enigma.loan_app.entity.LoanType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanRequest {
    private LoanType loanType;
    private InstalmentType instalmentType;
    private Customer customer;
    private Double nominal;
}

package com.enigma.loan_app.dto.response;

import com.enigma.loan_app.entity.LoanTransactionDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanTransactionDetailResponse {
    private String Id;
    private Date TransactionDate;
    private Double nominal;
    private LoanTransactionDetail.LoanStatus loanStatus;
    private Date createdAt;
    private Date updatedAt;
}

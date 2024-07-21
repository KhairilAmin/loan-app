package com.enigma.loan_app.dto.response;

import com.enigma.loan_app.entity.Customer;
import com.enigma.loan_app.entity.LoanTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanResponse {
    private String Id;
    private String loanTypeId;
    private String instalmentTypeId;
    private String customerId;
    private Double nominal;
    private Date approvedAt;
    private String approvedBy;
    private LoanTransaction.ApprovalStatus approvalStatus;
    private List<LoanTransactionDetailResponse> transactionDetailResponse;
    private Date createdAt;
    private Date updatedAt;
}

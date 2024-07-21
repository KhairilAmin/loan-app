package com.enigma.loan_app.dto.response;

import com.enigma.loan_app.entity.LoanTransactionDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private LoanTransactionDetailResponse loanTransactionDetail;
    private String name;
    private String url;
}

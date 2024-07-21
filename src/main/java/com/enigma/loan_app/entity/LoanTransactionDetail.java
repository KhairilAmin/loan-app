package com.enigma.loan_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "trx_loan_detail")
public class LoanTransactionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Date transactionDate;
    private Double nominal;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trx_loan_id", nullable = false)
    private LoanTransaction loanTransaction;
    private LoanStatus loanStatus = LoanStatus.UNPAID; // enum
    private Date createdAt;
    private Date updatedAt;

    public enum LoanStatus {
        PAID,
        UNPAID
    }

}

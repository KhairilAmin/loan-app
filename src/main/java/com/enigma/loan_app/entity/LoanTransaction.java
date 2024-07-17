package com.enigma.loan_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "trx_loan")

public class LoanTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "loan_type_id", nullable = false)
    private LoanType loanType;
    @ManyToOne
    @JoinColumn(name = "instalment_type_id", nullable = false)
    private InstalmentType instalmentType;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    private Double nominal;
    private Date approvedAt;
    private String approvedBy;
    private ApprovalStatus approvalStatus;// enum
    @OneToMany(mappedBy = "loanTransaction")
    private List<LoanTransactionDetail> loanTransactionDetails;
    private Date createdAt;
    private Date updatedAt;

    enum ApprovalStatus {
        APPROVED,
        REJECTED
    }
}

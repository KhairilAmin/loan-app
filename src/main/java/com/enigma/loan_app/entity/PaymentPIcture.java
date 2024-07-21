package com.enigma.loan_app.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "t_payment_picture")
public class PaymentPIcture {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private Long size;

    @Column(name = "url")
    private String url;

    @OneToOne
    @JoinColumn(name = "trx_detailid", nullable = false)
    private LoanTransactionDetail loanTransactionDetail;
}

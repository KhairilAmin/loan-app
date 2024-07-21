package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.constant.APIUrl;
import com.enigma.loan_app.dto.request.ApproveLoanRequest;
import com.enigma.loan_app.dto.request.LoanRequest;
import com.enigma.loan_app.dto.response.LoanResponse;
import com.enigma.loan_app.dto.response.LoanTransactionDetailResponse;
import com.enigma.loan_app.dto.response.PaymentResponse;
import com.enigma.loan_app.entity.*;
import com.enigma.loan_app.repository.*;
import com.enigma.loan_app.service.CustomerService;
import com.enigma.loan_app.service.LoanService;
import com.enigma.loan_app.service.UserService;
import com.enigma.loan_app.utils.NoSuchDataExistsException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;
    private final CustomerServiceImpl customerService;
    private final LoanTypeRepository loanTypeRepository;
    private final LoanTypeServiceImpl loanTypeService;
    private final InstalmentTypeRepository loanInstalmentTypeRepository;
    private final InstalmentTypeImpl instalmentTypeService;
    private final UserServiceImpl userService;
    private final LoanDetailRepository loanDetailRepository;
    private Path targetDirectory;
    private final PaymentPictureRepository paymentPictureRepository;
    @Override
    public LoanResponse createLoan(LoanRequest loanRequest) {
        Customer customer = customerService.findByidOrThrowNotFound(loanRequest.getCustomer().getId());
        LoanType loanType = loanTypeService.findByidOrThrowNotFound(loanRequest.getLoanType().getId());
        InstalmentType instalmentType = instalmentTypeService.findByidOrThrowNotFound(loanRequest.getInstalmentType().getId());

        LoanTransaction loanTransaction = new LoanTransaction();
        loanTransaction.setCustomer(customer);
        loanTransaction.setLoanType(loanType);
        loanTransaction.setInstalmentType(instalmentType);
        loanTransaction.setNominal(loanRequest.getNominal());
        loanTransaction.setCreatedAt(new Date());
        LoanTransaction response = loanRepository.saveAndFlush(loanTransaction);

        LoanResponse loanResponse = LoanResponse.builder()
                .Id(response.getId())
                .customerId(customer.getId())
                .loanTypeId(loanType.getId())
                .instalmentTypeId(instalmentType.getId())
                .nominal(response.getNominal())
                .createdAt(response.getCreatedAt())
                .build();
        return loanResponse;
    }

    @Override
    public LoanResponse approveLoan(ApproveLoanRequest approveLoanRequest, String adminId) {
        System.out.println(1);
        LoanTransaction loanTransaction = loanRepository.findById(approveLoanRequest.getLoanTransactionId()).orElse(null);
        System.out.println(2);
        if (loanTransaction == null) {
            throw new NoSuchDataExistsException("Installment Type not found or status is inactive");
        }
        System.out.println(3);
        LoanTransaction response;
        switch (loanTransaction.getInstalmentType().getInstalmentType().toString()) {
            case "ONE_MONTH":
                response = createLoanTransactionDetail(loanTransaction, 1, approveLoanRequest.getInterestRates(), adminId);
                break;
            case "THREE_MONTHS":
                response = createLoanTransactionDetail(loanTransaction, 3, approveLoanRequest.getInterestRates(), adminId);
                break;
            case "SIXTH_MONTHS":
                response = createLoanTransactionDetail(loanTransaction, 6, approveLoanRequest.getInterestRates(), adminId);
                break;
            case "NINE_MONTHS":
                response = createLoanTransactionDetail(loanTransaction, 9, approveLoanRequest.getInterestRates(), adminId);
                break;
            case "TWELVE_MONTHS":
                response = createLoanTransactionDetail(loanTransaction, 12, approveLoanRequest.getInterestRates(), adminId);
                break;
            default:
                throw new NoSuchDataExistsException("Installment Type not found or status is inactive");
        }
        System.out.println(4);
        LoanResponse loanResponse = LoanResponse.builder()
                .Id(response.getId())
                .customerId(response.getCustomer().getId())
                .loanTypeId(response.getLoanType().getId())
                .instalmentTypeId(response.getInstalmentType().getId())
                .nominal(response.getNominal())
                .createdAt(response.getCreatedAt())
                .approvedAt(response.getApprovedAt())
                .approvedBy(response.getApprovedBy())
                .approvalStatus(response.getApprovalStatus())
                .transactionDetailResponse(response.getLoanTransactionDetails().stream().map(this::convertToTranactionDetailsResponse).toList())
                .updatedAt(response.getUpdatedAt())
                .build();
        return loanResponse;
    }

    @Override
    public LoanResponse getLoanById(String loanId) {
        LoanTransaction loanTransaction = loanRepository.findById(loanId).orElse(null);
        if (loanTransaction == null) {
            throw new NoSuchDataExistsException("Installment Type not found or status is inactive");
        }
        LoanResponse loanResponse = LoanResponse.builder()
                .Id(loanTransaction.getId())
                .customerId(loanTransaction.getCustomer().getId())
                .loanTypeId(loanTransaction.getLoanType().getId())
                .instalmentTypeId(loanTransaction.getInstalmentType().getId())
                .nominal(loanTransaction.getNominal())
                .createdAt(loanTransaction.getCreatedAt())
                .approvedAt(loanTransaction.getApprovedAt())
                .approvedBy(loanTransaction.getApprovedBy())
                .approvalStatus(loanTransaction.getApprovalStatus())
                .transactionDetailResponse(loanTransaction.getLoanTransactionDetails().stream().map(this::convertToTranactionDetailsResponse).toList())
                .updatedAt(loanTransaction.getUpdatedAt())
                .build();


        return loanResponse;
    }

    @Override
    public PaymentResponse paymentPicture(MultipartFile file, String id) {
        this.targetDirectory = Path.of("assets/images/");
        try {
            Files.createDirectories(targetDirectory);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        String fileName = Objects.requireNonNull(file.getOriginalFilename());
        String idFileName = id + "_" + fileName;
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(APIUrl.TRANSACTION_API)
                .path("/payment-picture/")
                .path(idFileName)
                .toUriString();
        LoanTransactionDetail loanTransactionDetail = loanDetailRepository.findById(id).orElse(null);
        if (loanTransactionDetail == null) {
            throw new NoSuchDataExistsException("loanTransactionDetail not found or status is inactive");
        }
        loanTransactionDetail.setLoanStatus(LoanTransactionDetail.LoanStatus.PAID);
        loanTransactionDetail.setUpdatedAt(new Date());
        loanDetailRepository.saveAndFlush(loanTransactionDetail);

        PaymentPIcture paymentPIcture = new PaymentPIcture();
        paymentPIcture.setContentType(file.getContentType());
        paymentPIcture.setSize(file.getSize());
        paymentPIcture.setUrl(fileDownloadUri);
        paymentPIcture.setName(idFileName);
        paymentPIcture.setLoanTransactionDetail(loanTransactionDetail);

        paymentPictureRepository.saveAndFlush(paymentPIcture);

        PaymentResponse paymentResponse = PaymentResponse.builder()
                .loanTransactionDetail(convertToTranactionDetailsResponse(loanTransactionDetail))
                .name(paymentPIcture.getName())
                .url(paymentPIcture.getUrl())
                .build();
        return paymentResponse;
    }

    private LoanTransaction createLoanTransactionDetail(LoanTransaction loanTransaction,int iteraton,int interestRate,String adminId) {

        List<LoanTransactionDetail> transactionDetails = new ArrayList<>();
        User admin = userService.findByidOrThrowNotFound(adminId);
        Double nominal = (loanTransaction.getNominal()*interestRate)/iteraton;
        for (int i = 0; i < iteraton; i++) {
            LoanTransactionDetail loanTransactionDetail = new LoanTransactionDetail();
            loanTransactionDetail.setLoanTransaction(loanTransaction);
            loanTransactionDetail.setCreatedAt(new Date());
            loanTransactionDetail.setNominal(nominal);
            loanTransactionDetail.setTransactionDate(new Date());

            loanDetailRepository.saveAndFlush(loanTransactionDetail);

            transactionDetails.add(loanTransactionDetail);
        }
        loanTransaction.setLoanTransactionDetails(transactionDetails);
        loanTransaction.setUpdatedAt(new Date());
        loanTransaction.setApprovalStatus(LoanTransaction.ApprovalStatus.APPROVED);
        loanTransaction.setApprovedAt(new Date());
        loanTransaction.setApprovedBy(admin.getEmail());
        loanRepository.saveAndFlush(loanTransaction);

        return loanTransaction;
    }

    private LoanTransactionDetailResponse convertToTranactionDetailsResponse(LoanTransactionDetail loanTransactionDetail) {
        LoanTransactionDetailResponse loanTransactionDetailResponse = LoanTransactionDetailResponse.builder()
                .Id(loanTransactionDetail.getId())
                .createdAt(loanTransactionDetail.getCreatedAt())
                .loanStatus(loanTransactionDetail.getLoanStatus())
                .nominal(loanTransactionDetail.getNominal())
                .TransactionDate(loanTransactionDetail.getTransactionDate())
                .updatedAt(loanTransactionDetail.getUpdatedAt())
                .build();
        return loanTransactionDetailResponse;
    }

}

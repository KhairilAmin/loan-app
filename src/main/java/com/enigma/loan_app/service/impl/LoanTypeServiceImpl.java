package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.dto.request.LoanTypeRequest;
import com.enigma.loan_app.dto.response.LoanTypeResponse;
import com.enigma.loan_app.entity.InstalmentType;
import com.enigma.loan_app.entity.LoanType;
import com.enigma.loan_app.repository.LoanTypeRepository;
import com.enigma.loan_app.service.LoanTypeService;
import com.enigma.loan_app.utils.NoSuchDataExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoanTypeServiceImpl implements LoanTypeService {
    private final LoanTypeRepository loanTypeRepository;
    @Override
    public List<LoanTypeResponse> findAll() {
        List<LoanTypeResponse> list = loanTypeRepository.findAll().stream().map(this::convertToLoanTypeResponse).toList();
        return list;
    }

    @Override
    public LoanTypeResponse findById(String id) {
        LoanType loanType = findByidOrThrowNotFound(id);
        LoanTypeResponse loanTypeResponse = convertToLoanTypeResponse(loanType);
        return loanTypeResponse;
    }

    @Override
    public LoanTypeResponse update(LoanTypeRequest loanTypeRequest) {
        LoanType loanType = findByidOrThrowNotFound(loanTypeRequest.getId());
        loanType.setMaxLoan(loanTypeRequest.getMaxLoan());
        loanType.setType(loanTypeRequest.getType());

        LoanType loanTypeResponse = loanTypeRepository.save(loanType);
        return convertToLoanTypeResponse(loanTypeResponse);
    }

    @Override
    public LoanTypeResponse save(LoanTypeRequest loanTypeRequest) {
        LoanType loanType = new LoanType();
        loanType.setType(loanTypeRequest.getType());
        loanType.setMaxLoan(loanTypeRequest.getMaxLoan());

        LoanType loan = loanTypeRepository.saveAndFlush(loanType);

        LoanTypeResponse loanTypeResponse = convertToLoanTypeResponse(loan);

        return loanTypeResponse;
    }

    @Override
    public void delete(String id) {
        LoanType loanType = findByidOrThrowNotFound(id);
        loanTypeRepository.delete(loanType);

    }

    public LoanType findByidOrThrowNotFound(String id){
        LoanType loanType = loanTypeRepository.findById(id)
                .orElse(null);

        if (loanType == null) {
            throw new NoSuchDataExistsException("Installment Type not found or status is inactive");
        }

        return loanType;
    }

    private LoanTypeResponse convertToLoanTypeResponse(LoanType loanType) {
        return LoanTypeResponse.builder()
                .id(loanType.getId())
                .type(loanType.getType())
                .maxLoan(loanType.getMaxLoan())
                .build();
    }
}

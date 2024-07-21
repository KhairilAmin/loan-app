package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.dto.request.CustomerRequest;
import com.enigma.loan_app.dto.request.InstalmentTypeRequest;
import com.enigma.loan_app.dto.response.InstalmentTypeResponse;
import com.enigma.loan_app.entity.Customer;
import com.enigma.loan_app.entity.InstalmentType;
import com.enigma.loan_app.repository.InstalmentTypeRepository;
import com.enigma.loan_app.service.InstalmentTypeService;
import com.enigma.loan_app.utils.NoSuchDataExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class InstalmentTypeImpl implements InstalmentTypeService {
    private final InstalmentTypeRepository instalmentTypeRepository;

    @Override
    public InstalmentTypeResponse createInstalmentType(InstalmentTypeRequest request) {
        InstalmentType instalmentType = new InstalmentType();
        instalmentType.setInstalmentType(request.getInstalmentType());
        InstalmentType instalment = instalmentTypeRepository.saveAndFlush(instalmentType);

        return convertToInstalmentTypeResponse(instalment);
    }

    @Override
    public List<InstalmentTypeResponse> getAllInstalmentType() {
        List<InstalmentTypeResponse> list = instalmentTypeRepository.findAll()
                .stream()
                .map(this::convertToInstalmentTypeResponse)
                .toList();

        return list;
    }

    @Override
    public InstalmentTypeResponse getByIdInstalmentType(String id) {
        findByidOrThrowNotFound(id);
        return convertToInstalmentTypeResponse(instalmentTypeRepository.findById(id).get());
    }

    @Override
    public InstalmentTypeResponse updateInstalmentType(InstalmentTypeRequest request) {
        InstalmentType instalment = findByidOrThrowNotFound(request.getId());
        instalment.setInstalmentType(request.getInstalmentType());
        InstalmentType instalmentType = instalmentTypeRepository.saveAndFlush(instalment);
        return convertToInstalmentTypeResponse(instalmentType);
    }

    @Override
    public String deleteInstalmentType(String id) {
        InstalmentType instalmentType = findByidOrThrowNotFound(id);
        instalmentTypeRepository.delete(instalmentType);
        return "Delete Instalment Type Successfully";
    }

    public InstalmentType findByidOrThrowNotFound(String id) {
        InstalmentType instalmentType = instalmentTypeRepository.findById(id)
                .orElse(null);

        if (instalmentType == null) {
            throw new NoSuchDataExistsException("Installment Type not found or status is inactive");
        }

        return instalmentType;
    }


    private InstalmentTypeResponse convertToInstalmentTypeResponse(InstalmentType instalmentType) {
        return InstalmentTypeResponse.builder()
                .id(instalmentType.getId())
                .instalmentType(instalmentType.getInstalmentType())
                .build();
    }


}

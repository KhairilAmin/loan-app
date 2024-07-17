package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.dto.request.CustomerRequest;
import com.enigma.loan_app.dto.request.InstalmentTypeRequest;
import com.enigma.loan_app.dto.response.InstalmentTypeResponse;
import com.enigma.loan_app.entity.InstalmentType;
import com.enigma.loan_app.repository.InstalmentTypeRepository;
import com.enigma.loan_app.service.InstalmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class InstalmentTypeImpl implements InstalmentTypeService {
    InstalmentTypeRepository instalmentTypeRepository;
    @Override
    public InstalmentTypeResponse createInstalmentType(InstalmentTypeRequest request) {
        InstalmentType tes = new InstalmentType();
        tes.setInstalmentType(request.getInstalmentType());
        instalmentTypeRepository.saveAndFlush(tes);

        return convertToInstalmentTypeResponse(tes);
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
        return null;
    }

    private InstalmentTypeResponse convertToInstalmentTypeResponse(InstalmentType instalmentType) {
        return InstalmentTypeResponse.builder()
                .id(instalmentType.getId())
                .instalmentType(instalmentType.getInstalmentType())
                .build();
    }


}

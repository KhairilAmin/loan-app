package com.enigma.loan_app.service;

import com.enigma.loan_app.dto.request.CustomerRequest;
import com.enigma.loan_app.dto.request.InstalmentTypeRequest;
import com.enigma.loan_app.dto.response.CustomerResponse;
import com.enigma.loan_app.dto.response.InstalmentTypeResponse;

import java.util.List;

public interface InstalmentTypeService {
    InstalmentTypeResponse createInstalmentType(InstalmentTypeRequest request);
    List<InstalmentTypeResponse> getAllInstalmentType();
    InstalmentTypeResponse getByIdInstalmentType(String id);
    InstalmentTypeResponse updateInstalmentType(InstalmentTypeRequest request);
    String deleteInstalmentType(String id);
}

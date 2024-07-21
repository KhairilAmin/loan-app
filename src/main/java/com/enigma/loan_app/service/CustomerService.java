package com.enigma.loan_app.service;

import com.enigma.loan_app.dto.request.CustomerRequest;
import com.enigma.loan_app.dto.response.CustomerResponse;
import com.enigma.loan_app.dto.response.ProfilePictureResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse getCustomer(String id);
    CustomerResponse updateCustomer(CustomerRequest request);
    ProfilePictureResponse UploadProfileAvatar(MultipartFile file, String id);
    Resource getFile(String name);
    String deleteCustomer(String id);
}

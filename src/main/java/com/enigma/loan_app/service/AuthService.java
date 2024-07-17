package com.enigma.loan_app.service;

import com.enigma.loan_app.dto.request.AuthRequest;
import com.enigma.loan_app.dto.request.CustomerRequest;
import com.enigma.loan_app.dto.response.RegisterResponse;
import com.enigma.loan_app.entity.Role;

import java.util.Map;

public interface AuthService {
    RegisterResponse registerCustomer(AuthRequest<CustomerRequest> authRequest);
    RegisterResponse register(AuthRequest<Map<String, Role.ERole>> authRequest);
}

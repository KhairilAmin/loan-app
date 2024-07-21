package com.enigma.loan_app.controller;

import com.enigma.loan_app.constant.APIUrl;
import com.enigma.loan_app.dto.request.AuthRequest;
import com.enigma.loan_app.dto.request.CustomerRequest;
import com.enigma.loan_app.dto.response.CommonResponse;
import com.enigma.loan_app.dto.response.LoginResponse;
import com.enigma.loan_app.dto.response.RegisterResponse;
import com.enigma.loan_app.entity.Role;
import com.enigma.loan_app.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIUrl.AUTH_API)
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup/customer")
    public ResponseEntity<CommonResponse<RegisterResponse>> registerCustumer(@RequestBody AuthRequest<CustomerRequest> request) {
        RegisterResponse registerResponse = authService.registerCustomer(request);

        CommonResponse<RegisterResponse> customerRegistered = CommonResponse.<RegisterResponse>builder()
                .message("Customer registered")
                .data(Optional.of(registerResponse))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(customerRegistered);
    }

    @PostMapping("/signup") // untuk staff dan admin
    public ResponseEntity<CommonResponse<RegisterResponse>> register(@RequestBody AuthRequest<Map<String, Role.ERole>> request) {
        RegisterResponse registerResponse = authService.register(request);

        CommonResponse<RegisterResponse> accountRegistered = CommonResponse.<RegisterResponse>builder()
                .message(registerResponse.getRole().name() + " registered")
                .data(Optional.of(registerResponse))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(accountRegistered);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse<LoginResponse>> login(@RequestBody AuthRequest<String> request){
        LoginResponse response = authService.login(request);
        CommonResponse<LoginResponse> commonResponse = CommonResponse.<LoginResponse>builder()
                .message("Success Login")
                .data(Optional.empty())
                .data(Optional.of(response))
                .build();
        return ResponseEntity.ok(commonResponse);

    }
}

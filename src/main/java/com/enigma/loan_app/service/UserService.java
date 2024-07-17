package com.enigma.loan_app.service;

import com.enigma.loan_app.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAll();
    UserResponse getById(String id);

}

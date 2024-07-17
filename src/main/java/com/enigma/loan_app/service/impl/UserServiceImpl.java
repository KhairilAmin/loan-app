package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.dto.response.UserResponse;
import com.enigma.loan_app.entity.Role;
import com.enigma.loan_app.entity.User;
import com.enigma.loan_app.repository.UserRepository;
import com.enigma.loan_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream().map(this::convertToUserResponse).toList();
    }

    @Override
    public UserResponse getById(String id) {
        User user = findByidOrThrowNotFound(id);
        return convertToUserResponse(user);
    }

    private User findByidOrThrowNotFound(String id){
        return userRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
    }

    private UserResponse convertToUserResponse(User user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .role(user.getRoles().stream().map(Role::getName).toArray())
                .build();
    }
}
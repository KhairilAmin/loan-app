package com.enigma.loan_app.controller;

import com.enigma.loan_app.constant.APIUrl;
import com.enigma.loan_app.dto.response.CommonResponse;
import com.enigma.loan_app.dto.response.UserResponse;
import com.enigma.loan_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIUrl.USER_API)
public class UserController {
    private final UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<UserResponse>> findById(@PathVariable("id") String id) {
        UserResponse userResponse = userService.getById(id);
        CommonResponse commonResponse = CommonResponse.builder()
                .message("User found!")
                .data(Optional.of(userResponse))
                .build();

        return ResponseEntity.status(HttpStatus.FOUND).body(commonResponse);
    }
}

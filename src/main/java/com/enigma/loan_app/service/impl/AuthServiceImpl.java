package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.dto.request.AuthRequest;
import com.enigma.loan_app.dto.request.CustomerRequest;
import com.enigma.loan_app.dto.response.RegisterResponse;
import com.enigma.loan_app.entity.Customer;
import com.enigma.loan_app.entity.ProfilePicture;
import com.enigma.loan_app.entity.Role;
import com.enigma.loan_app.entity.User;
import com.enigma.loan_app.repository.UserRepository;
import com.enigma.loan_app.service.AuthService;
import com.enigma.loan_app.service.CustomerService;
import com.enigma.loan_app.service.RoleService;
import com.enigma.loan_app.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final CustomerService customerService;

    @Override
    public RegisterResponse registerCustomer(AuthRequest<CustomerRequest> authRequest) {
        Role role = roleService.getOrSave(Role.ERole.ROLE_CUSTOMER);
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        User user = User.builder().email(authRequest.getEmail())
                .password(authRequest.getPassword())
                .roles(roles)
                .build();

        userRepository.save(user);

        authRequest.getData().get().setUser(user);

        customerService.createCustomer(authRequest.getData().orElseThrow(
                () -> new ResourceNotFoundException("Customer not found")
        ));
        return RegisterResponse.builder()
                .email(user.getEmail())
                .role(role.getName())
                .build();

    }

    @Override
    public RegisterResponse register(AuthRequest<Map<String, Role.ERole>> authRequest) {

        Role role = roleService.getOrSave(authRequest.getData().get().get("role"));
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        User user = User.builder().email(authRequest.getEmail())
                .password(authRequest.getPassword())
                .roles(roles)
                .build();

        userRepository.save(user);

        return RegisterResponse.builder()
                .email(user.getEmail())
                .role(role.getName())
                .build();
    }


}

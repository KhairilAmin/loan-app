package com.enigma.loan_app.service.impl;

import com.auth0.jwt.algorithms.Algorithm;
import com.enigma.loan_app.dto.request.AuthRequest;
import com.enigma.loan_app.dto.request.CustomerRequest;
import com.enigma.loan_app.dto.response.LoginResponse;
import com.enigma.loan_app.dto.response.RegisterResponse;
import com.enigma.loan_app.entity.*;
import com.enigma.loan_app.repository.UserRepository;
import com.enigma.loan_app.security.JwtUtil;
import com.enigma.loan_app.service.AuthService;
import com.enigma.loan_app.service.CustomerService;
import com.enigma.loan_app.service.RoleService;
import com.enigma.loan_app.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    @Override
    public RegisterResponse registerCustomer(AuthRequest<CustomerRequest> authRequest) {
        Role role = roleService.getOrSave(Role.ERole.ROLE_CUSTOMER);
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        User user = User.builder().email(authRequest.getEmail())
                .password(passwordEncoder.encode(authRequest.getPassword()))
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
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .roles(roles)
                .build();

        userRepository.save(user);

        return RegisterResponse.builder()
                .email(user.getEmail())
                .role(role.getName())
                .build();
    }

    @Override
    public LoginResponse login(AuthRequest<String> authRequest) {
        // Authentication Manager
        System.out.println(1);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println(3);
        AppUser appUser = (AppUser) authentication.getPrincipal();
        System.out.println(4);
        // TODO: Generate Token!
        Algorithm algorithm = Algorithm.HMAC256("secret-key");
        String token = jwtUtil.generateToken(appUser);

        return LoginResponse.builder()
                .token(token)
                .email(appUser.getEmail())
                .role(appUser.getRole())
                .build();
    }


}

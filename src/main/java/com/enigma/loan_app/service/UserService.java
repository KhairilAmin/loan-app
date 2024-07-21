package com.enigma.loan_app.service;

import com.enigma.loan_app.dto.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface  UserService extends UserDetailsService {
    List<UserResponse> getAll();
    UserResponse getById(String id);
    public UserDetails loadUserById(String id) throws UsernameNotFoundException;

}

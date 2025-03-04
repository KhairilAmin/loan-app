package com.enigma.loan_app.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequest<T> {
    private String email;
    private String password;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Optional<T> data;
}

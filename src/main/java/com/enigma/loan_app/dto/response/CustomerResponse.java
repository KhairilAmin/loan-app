package com.enigma.loan_app.dto.response;

import com.enigma.loan_app.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private String id;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date dateOfBirth;
    private String phone;
    private String status;
}

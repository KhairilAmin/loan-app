package com.enigma.loan_app.dto.response;

import com.enigma.loan_app.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfilePictureResponse {
    private String name;
    private String url;
}

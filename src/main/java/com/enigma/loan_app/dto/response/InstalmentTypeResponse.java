package com.enigma.loan_app.dto.response;

import com.enigma.loan_app.entity.InstalmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstalmentTypeResponse {
    private String id;
    private InstalmentType.EInstalmentType instalmentType;
}
package com.enigma.loan_app.dto.request;

import com.enigma.loan_app.entity.InstalmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstalmentTypeRequest {
    private InstalmentType.EInstalmentType instalmentType;
}

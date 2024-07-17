package com.enigma.loan_app.controller;

import com.enigma.loan_app.constant.APIUrl;
import com.enigma.loan_app.dto.response.CommonResponse;
import com.enigma.loan_app.dto.response.CustomerResponse;
import com.enigma.loan_app.dto.response.InstalmentTypeResponse;
import com.enigma.loan_app.service.InstalmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIUrl.INSTALMENT_API)
public class InstalmentTypeController {
    InstalmentTypeService instalmentTypeService;
    @GetMapping
    public ResponseEntity<CommonResponse<List<InstalmentTypeResponse>>> getCustomer() {
        List<InstalmentTypeResponse> instalmentType = instalmentTypeService.getAllInstalmentType();

        CommonResponse<List<InstalmentTypeResponse>> commonResponse = new CommonResponse<>();
        commonResponse.setData(Optional.ofNullable(instalmentType));
        commonResponse.setMessage("Data of Instalment Type");

        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @GetMapping("/{id}")
        public ResponseEntity<CommonResponse<InstalmentTypeResponse>> getCustomer(@PathVariable String id) {
            InstalmentTypeResponse customerResponse = instalmentTypeService.getByIdInstalmentType(id);

                CommonResponse<InstalmentTypeResponse> commonResponse = CommonResponse.<InstalmentTypeResponse>builder()
                .message("Instalment Type Found!")
                .data(Optional.of(customerResponse))
                .build();

        return ResponseEntity.status(HttpStatus.FOUND).body(commonResponse);
    }

}

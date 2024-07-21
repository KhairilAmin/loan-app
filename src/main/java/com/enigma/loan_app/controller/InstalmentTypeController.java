package com.enigma.loan_app.controller;

import com.enigma.loan_app.constant.APIUrl;
import com.enigma.loan_app.dto.request.InstalmentTypeRequest;
import com.enigma.loan_app.dto.response.CommonResponse;
import com.enigma.loan_app.dto.response.CustomerResponse;
import com.enigma.loan_app.dto.response.InstalmentTypeResponse;
import com.enigma.loan_app.service.InstalmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIUrl.INSTALMENT_API)
public class InstalmentTypeController {
    private final InstalmentTypeService instalmentTypeService;

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
            InstalmentTypeResponse installamentType = instalmentTypeService.getByIdInstalmentType(id);

                CommonResponse<InstalmentTypeResponse> commonResponse = CommonResponse.<InstalmentTypeResponse>builder()
                .message("Instalment Type Found!")
                .data(Optional.of(installamentType))
                .build();

        return ResponseEntity.status(HttpStatus.FOUND).body(commonResponse);
    }

    @PostMapping
        public ResponseEntity<CommonResponse<InstalmentTypeResponse>> createInstallmentType(@RequestBody InstalmentTypeRequest instalmentTypeRequest) {
        InstalmentTypeResponse instalmentTypeResponse = instalmentTypeService.createInstalmentType(instalmentTypeRequest);

        CommonResponse<InstalmentTypeResponse> commonResponse = CommonResponse.<InstalmentTypeResponse>builder()
                .message("Instalment Type Created!")
                .data(Optional.of(instalmentTypeResponse))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<InstalmentTypeResponse>> updateInstalmentType(@RequestBody InstalmentTypeRequest instalmentTypeRequest) {
        InstalmentTypeResponse instalmentTypeResponse = instalmentTypeService.updateInstalmentType(instalmentTypeRequest);

        CommonResponse<InstalmentTypeResponse> commonResponse = CommonResponse.<InstalmentTypeResponse>builder()
                .message("Instalment Type Updated!")
                .data(Optional.of(instalmentTypeResponse))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<String>> deleteInstalmentType(@PathVariable String id) {
        String instalmentType = instalmentTypeService.deleteInstalmentType(id);
        CommonResponse<String> commonResponse = CommonResponse.<String>builder()
                .message(instalmentType)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }
}

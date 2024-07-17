package com.enigma.loan_app.controller;

import com.enigma.loan_app.constant.APIUrl;
import com.enigma.loan_app.dto.request.CustomerRequest;
import com.enigma.loan_app.dto.response.CommonResponse;
import com.enigma.loan_app.dto.response.CustomerResponse;
import com.enigma.loan_app.dto.response.ProfilePictureResponse;
import com.enigma.loan_app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIUrl.CUSTOMER_API)
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<CustomerResponse>>> getCustomer() {
        List<CustomerResponse> customerResponses = customerService.getAllCustomers();

        CommonResponse<List<CustomerResponse>> commonResponse = new CommonResponse<>();
        commonResponse.setData(Optional.ofNullable(customerResponses));
        commonResponse.setMessage("Data of Customer");

        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<CustomerResponse>> getCustomer(@PathVariable String id) {
        CustomerResponse customerResponse = customerService.getCustomer(id);

        CommonResponse<CustomerResponse> commonResponse = CommonResponse.<CustomerResponse>builder()
                .message("Customer Found!")
                .data(Optional.of(customerResponse))
                .build();

        return ResponseEntity.status(HttpStatus.FOUND).body(commonResponse);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<CustomerResponse>> updateCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.updateCustomer(customerRequest);

        CommonResponse<CustomerResponse> commonResponse = CommonResponse.<CustomerResponse>builder()
                .message("Customer Updated!")
                .data(Optional.of(customerResponse))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @PostMapping("/{id}/upload/avatar")
    public ResponseEntity<CommonResponse<ProfilePictureResponse>> uploadAvatar(@PathVariable String id, @RequestParam("file") MultipartFile file) {
        ProfilePictureResponse profilePictureResponse = customerService.UploadProfileAvatar(file,id);

        CommonResponse<ProfilePictureResponse> commonResponse = CommonResponse.<ProfilePictureResponse>builder()
                .message("Profile Picture Uploaded!")
                .data(Optional.of(profilePictureResponse))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @GetMapping("/profilepicture/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) {
        Resource resource = customerService.getFile(fileName);
        if(resource == null) {
            CommonResponse<String> responseNotFound = CommonResponse.<String>builder()
                    .message("Gambar tidak ada")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(responseNotFound);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}

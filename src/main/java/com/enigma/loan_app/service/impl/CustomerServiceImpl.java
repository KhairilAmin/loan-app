package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.constant.APIUrl;
import com.enigma.loan_app.dto.request.CustomerRequest;
import com.enigma.loan_app.dto.response.CustomerResponse;
import com.enigma.loan_app.dto.response.ProfilePictureResponse;
import com.enigma.loan_app.entity.Customer;
import com.enigma.loan_app.entity.ProfilePicture;
import com.enigma.loan_app.repository.CustomerRepository;
import com.enigma.loan_app.repository.ProfilePictureRepository;
import com.enigma.loan_app.service.CustomerService;
import com.enigma.loan_app.utils.NoSuchDataExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ProfilePictureRepository profilePictureRepository;
    private Path targetDirectory;
    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
        // Create entitas baru daru customerRequest
        Customer customer =  new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setBirthOfDate(request.getDateOfBirth());
        customer.setPhone(request.getPhone());
        customer.setStatus(request.getStatus());
        customer.setUser(request.getUser());

        // Save customer
        customer = customerRepository.saveAndFlush(customer);
        return convertToCustomerResponse(customer);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        List<CustomerResponse> customers = customerRepository.findAllByDeletedFalse().stream().map(this::convertToCustomerResponse).toList();
        return customers;
    }

    @Override
    public CustomerResponse getCustomer(String id) {
        Customer customer = findByidOrThrowNotFound(id);
        return convertToCustomerResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomer(CustomerRequest request) {
        findByidOrThrowNotFound(request.getId());

        Customer customer = findByidOrThrowNotFound(request.getId());
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setBirthOfDate(request.getDateOfBirth());
        customer.setPhone(request.getPhone());
        customer.setStatus(request.getStatus());
        customer = customerRepository.saveAndFlush(customer);
        return convertToCustomerResponse(customer);
    }

    @Override
    public ProfilePictureResponse UploadProfileAvatar(MultipartFile file, String id) {
        this.targetDirectory = Path.of("assets/images/");
        try {
            Files.createDirectories(targetDirectory);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        String fileName = Objects.requireNonNull(file.getOriginalFilename());
        String idFileName = id + "_" + fileName;

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(APIUrl.CUSTOMER_API)
                .path("/profilepicture/")
                .path(idFileName)
                .toUriString();

        Customer customer = findByidOrThrowNotFound(id);

        ProfilePicture profilePicture = ProfilePicture.builder()
                .customer(customer)
                .url(fileDownloadUri)
                .name(idFileName)
                .size(file.getSize())
                .contentType(file.getContentType())
                .build();

        profilePictureRepository.saveAndFlush(profilePicture);


        return ProfilePictureResponse.builder()
                .name(idFileName)
                .url(fileDownloadUri)
                .build();
    }

    @Override
    public Resource getFile(String name) {
        this.targetDirectory = Path.of("assets/images/");
        try {
            Files.createDirectories(targetDirectory);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        try {
            Path pathfile = Paths.get(this.targetDirectory.toUri()).resolve(name).normalize();
            Resource resource = new UrlResource(pathfile.toUri());
            if (resource.exists()) {
                return resource;
            }else {
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public String deleteCustomer(String id) {
        System.out.println(1);
        findByidOrThrowNotFound(id);
        System.out.println(2);
        customerRepository.findById(id).ifPresent(customer -> {
            customer.setDeleted(true);
            customerRepository.save(customer);
        });
        return "Deleted Customer with id: " + id;
    }


    public Customer findByidOrThrowNotFound(String id) {
        Customer customer = customerRepository.findById(id)
                .orElse(null);

        if (customer == null || customer.isDeleted()) {
            throw new NoSuchDataExistsException("Customer not found or status is inactive");
        }

        return customer;
    }

    private CustomerResponse convertToCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .dateOfBirth(customer.getBirthOfDate())
                .phone(customer.getPhone())
                .status(customer.getStatus())
                .build();

    }
}

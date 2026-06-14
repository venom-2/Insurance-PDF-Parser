package com.pdf.parser.customer.controller;

import com.pdf.parser.customer.entity.Customer;
import com.pdf.parser.customer.service.CustomerService;

import com.pdf.parser.common.response.ApiResponse;
import com.pdf.parser.customer.dto.CustomerRequest;
import com.pdf.parser.customer.dto.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customers/")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ApiResponse<?>  createCustomer(@Valid @RequestBody CustomerRequest request) {
        return new ApiResponse<CustomerRequest>(true, "Customer created successfully!", request);
    }

    @GetMapping("/{id}")
    public ApiResponse<?> getCustomer(@PathVariable Long id) {

        Customer customer = customerService.getCustomer(id);

        CustomerResponse response = CustomerResponse.builder()
                .id(customer.getId())
                .companyName(customer.getCompanyName())
                .email(customer.getEmail())
                .active(customer.getActive())
                .build();

        return new ApiResponse<>(
                true,
                "Customer fetched successfully!",
                response
        );
    }
}

package com.pdf.parser.customer.controller;

import com.pdf.parser.customer.service.CustomerService;

import com.pdf.parser.common.response.ApiResponse;
import com.pdf.parser.customer.dto.CustomerRequest;
import com.pdf.parser.customer.dto.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers/")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createCustomer(@Valid @RequestBody CustomerRequest request) {
        CustomerResponse customer = customerService.saveCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Customer created successfully!",
                        customer
                ));
    }

    @GetMapping()
    public ApiResponse<?> getCustomers() {
        List<CustomerResponse> customers = customerService.getCustomers();
        return new ApiResponse<List<CustomerResponse>>(true, "Customers fetched successfully!", customers);
    }

    @GetMapping("/{id}")
    public ApiResponse<?> getCustomers(@PathVariable Long id) {

        CustomerResponse customer = customerService.getCustomer(id);

        return new ApiResponse<CustomerResponse>(
                true,
                "Customer fetched successfully!",
                customer
        );
    }
}

package com.pdf.parser.customer.service;

import com.pdf.parser.common.exception.CustomerNotFoundException;
import com.pdf.parser.common.translator.CustomerTranslator;
import com.pdf.parser.customer.dto.CustomerRequest;
import com.pdf.parser.customer.dto.CustomerResponse;
import com.pdf.parser.customer.entity.Customer;
import com.pdf.parser.customer.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerTranslator customerTranslator;

    public List<CustomerResponse> getCustomers() {
        return customerTranslator.toCustomerResponse(customerRepository.findAll());
    }

    public CustomerResponse getCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException(id));

        return customerTranslator.toCustomerResponse(customer);
    }

    public CustomerResponse saveCustomer(CustomerRequest request) {
        Customer customer = customerTranslator.toCustomer(request);
        // Explicitly set active status true
        customer.setActive(true);
        return customerTranslator.toCustomerResponse(customerRepository.save(customer));
    }
}
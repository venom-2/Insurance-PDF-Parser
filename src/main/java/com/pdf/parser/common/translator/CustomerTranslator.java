package com.pdf.parser.common.translator;

import com.pdf.parser.customer.dto.CustomerRequest;
import com.pdf.parser.customer.dto.CustomerResponse;
import com.pdf.parser.customer.entity.Customer;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Component
public class CustomerTranslator {

    private final ObjectMapper objectMapper;

    public CustomerTranslator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public CustomerResponse toCustomerResponse(Customer customer) {
        return objectMapper.convertValue(customer, CustomerResponse.class);
    }

    public List<CustomerResponse> toCustomerResponse(List<Customer> customers) {
        return customers.stream()
                .map(this::toCustomerResponse)
                .toList();
    }

    public Customer toCustomer(CustomerRequest customerRequest) {
        return objectMapper.convertValue(customerRequest, Customer.class);
    }
}

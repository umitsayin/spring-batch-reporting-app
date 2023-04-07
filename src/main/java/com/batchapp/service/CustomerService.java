package com.batchapp.service;

import com.batchapp.api.request.CreateCustomerRequest;
import com.batchapp.api.request.UpdateCustomerRequest;
import com.batchapp.dto.CustomerDto;
import com.batchapp.model.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAll();
    CustomerDto getById(int id);
    CustomerDto add(CreateCustomerRequest request);
    CustomerDto updateById(int id, UpdateCustomerRequest request);
    void deleteById(int id);
    Customer getCustomerById(int id);
}

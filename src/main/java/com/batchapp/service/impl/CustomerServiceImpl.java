package com.batchapp.service.impl;

import com.batchapp.api.request.CreateCustomerRequest;
import com.batchapp.api.request.UpdateCustomerRequest;
import com.batchapp.constant.GlobalConstant;
import com.batchapp.dto.CustomerDto;
import com.batchapp.exception.EntityAlreadyExistsException;
import com.batchapp.exception.EntityNotFoundException;
import com.batchapp.model.Customer;
import com.batchapp.repository.CustomerRepository;
import com.batchapp.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CustomerDto> getAll() {
        return repository.findAll().stream().map(CustomerDto::convert).toList();
    }

    @Override
    public CustomerDto getById(int id) {
        Customer customer = findById(id);

        return CustomerDto.convert(customer);
    }

    @Override
    public CustomerDto add(CreateCustomerRequest request) {
        checkIfCustomerEmailExists(request.getEmail());

        Customer customer = new Customer();

        customer.setFirstname(request.getFirstname());
        customer.setLastname(request.getLastname());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());

        return CustomerDto.convert(repository.save(customer));
    }

    @Override
    public CustomerDto updateById(int id, UpdateCustomerRequest request) {
        Customer customer = findById(id);

        customer.setFirstname(request.getFirstname());
        customer.setLastname(request.getLastname());
        customer.setPhone(request.getPhone());

        return CustomerDto.convert(repository.save(customer));
    }

    @Override
    public void deleteById(int id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        return findById(id);
    }

    private Customer findById(int id){
        return repository.findById(id).orElseThrow(()-> new EntityNotFoundException(GlobalConstant.CUSTOMER_NOT_FOUND));
    }

    private void checkIfCustomerEmailExists(String email){
        if(repository.existsCustomerByEmail(email)){
            throw new EntityAlreadyExistsException(GlobalConstant.CUSTOMER_ALREADY_EXISTS);
        }
    }
}

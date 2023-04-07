package com.batchapp.api;

import com.batchapp.api.request.CreateCustomerRequest;
import com.batchapp.api.request.UpdateCustomerRequest;
import com.batchapp.dto.CustomerDto;
import com.batchapp.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/customers")
public class CustomersController {
    private final CustomerService customerService;

    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll(){
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable int id){
        return ResponseEntity.ok(customerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> add(CreateCustomerRequest request){
        return ResponseEntity.ok(customerService.add(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateById(@PathVariable int id, UpdateCustomerRequest request){
        return ResponseEntity.ok(customerService.updateById(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id){
        customerService.deleteById(id);
    }
}

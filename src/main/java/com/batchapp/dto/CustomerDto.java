package com.batchapp.dto;

import com.batchapp.model.Customer;

public record CustomerDto(int id, String firstname, String lastname, String email, String phone) {
    public static CustomerDto convert(Customer from){
        return new CustomerDto(
                from.getId(),
                from.getFirstname(),
                from.getLastname(),
                from.getEmail(),
                from.getPhone()
        );
    }
}

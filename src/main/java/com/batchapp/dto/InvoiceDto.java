package com.batchapp.dto;

import com.batchapp.model.Invoice;

public record InvoiceDto(int id, String name, double totalPrice, String currency, String customerEmail) {
    public static InvoiceDto convert(Invoice from){
        return new InvoiceDto(
                from.getId(),
                from.getName(),
                from.getTotalPrice(),
                from.getCurrency(),
                from.getCustomer().getEmail()
        );
    }
}

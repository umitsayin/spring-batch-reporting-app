package com.batchapp.config.batch.steps;

import com.batchapp.dto.InvoiceDto;
import com.batchapp.model.Invoice;
import org.springframework.batch.item.ItemProcessor;

public class InvoiceProcessor implements ItemProcessor<Invoice, InvoiceDto> {

    @Override
    public InvoiceDto process(Invoice item) throws Exception {
        return InvoiceDto.convert(item);
    }
}
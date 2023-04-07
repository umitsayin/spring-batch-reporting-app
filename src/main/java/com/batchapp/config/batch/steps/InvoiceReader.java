package com.batchapp.config.batch.steps;

import com.batchapp.model.Invoice;
import com.batchapp.repository.InvoiceRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.data.domain.Sort;

import java.util.HashMap;

public class InvoiceReader extends RepositoryItemReader<Invoice> implements ItemReader<Invoice> {
    public InvoiceReader(InvoiceRepository repository){
        setRepository(repository);
        setMethodName("findAll");

        final HashMap<String, Sort.Direction> sorts = new HashMap<>();
        sorts.put("id", Sort.Direction.ASC);
        setSort(sorts);
    }
}
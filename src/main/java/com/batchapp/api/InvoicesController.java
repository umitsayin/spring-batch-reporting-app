package com.batchapp.api;

import com.batchapp.api.request.CreateInvoiceRequest;
import com.batchapp.api.request.UpdateInvoiceRequest;
import com.batchapp.dto.InvoiceDto;
import com.batchapp.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/invoies")
public class InvoicesController {
    private final InvoiceService invoiceService;

    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDto>> getAll(){
        return ResponseEntity.ok(invoiceService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDto> getById(@PathVariable int id){
        return ResponseEntity.ok(invoiceService.getById(id));
    }

    @PostMapping
    public ResponseEntity<InvoiceDto> add(CreateInvoiceRequest request){
        return ResponseEntity.ok(invoiceService.add(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDto> updateById(@PathVariable int id, UpdateInvoiceRequest request){
        return ResponseEntity.ok(invoiceService.updateById(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id){
        invoiceService.deleteById(id);
    }
}

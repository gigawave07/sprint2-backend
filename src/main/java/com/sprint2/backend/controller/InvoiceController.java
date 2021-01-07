package com.sprint2.backend.controller;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.Invoice;
import com.sprint2.backend.services.invoice.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoice")
@CrossOrigin
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;

    /**
     * nguyen quoc khanh
     * @param id
     * @param page
     * @return
     * get invoice list with sort and pagination
     */
    @GetMapping("/find-all-invoice-by-customerId/{id}")
    public ResponseEntity<Page<Invoice>> getAllCarByCustomerId(@PathVariable Long id,
                                                               @RequestParam(name = "page", defaultValue = "0", required = false)
                                                                   int page) {
        Page<Invoice> invoices = this.invoiceService.findAllByCustomerID(id, page);
        if (invoices.isEmpty()) {
            return new ResponseEntity<>(invoices, HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(invoices);
    }
}

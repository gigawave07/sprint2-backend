package com.sprint2.backend.controller;

import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.model.InformationCustomerDTO;
import com.sprint2.backend.model.ListEntryLogDTO;
import com.sprint2.backend.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    //-- Đin ---
    @Autowired
    CustomerService customerService;

    // Update Customer
    @PutMapping("/update")
    public ResponseEntity<?> updateCustomer(@Validated @RequestBody InformationCustomerDTO customerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
        this.customerService.updateCustomer(customerDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Tìm kiếm customer theo account
    @GetMapping("/find-by-account/{accountId}")
    public ResponseEntity<Customer> getByAccount(@PathVariable Long accountId) {
        return new ResponseEntity<>(this.customerService.findByAppAccount(accountId), HttpStatus.OK);
    }

    // Hiển thị lịch sử xe ra vào của khách hàng và phân trang
    @GetMapping("/list-entry-log/{accountId}/{pageable}")
    public ResponseEntity<Page<ListEntryLogDTO>> getListEntryLog(@PathVariable Long accountId, @PathVariable int pageable) {
        return new ResponseEntity<>(this.customerService.findListEntryLog(accountId, pageable), HttpStatus.OK);
    }
    // End
}

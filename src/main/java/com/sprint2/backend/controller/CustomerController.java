package com.sprint2.backend.controller;

import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    CustomerService customerService;

    /**
     * nguyen quoc khanh
     * @param id
     * @return
     * get customer by account id
     */
    @GetMapping("/find-customer-by-accountId/{id}")
    public ResponseEntity<Customer> getCustomerByAccountId(@PathVariable Long id) {
        Customer customer = this.customerService.findCustomerByAppAccountId(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}

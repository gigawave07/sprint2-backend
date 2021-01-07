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
    // --------------------Vinh begin -----------------------
    @Autowired
    private CustomerService customerService;

    @GetMapping("/getCustomerDetail/{accountId}")
    public ResponseEntity<Customer> getCustomerDetail(@PathVariable Long accountId) {
        return ResponseEntity.ok(this.customerService.findByAccountId(accountId));
    }

    // --------------------Vinh end -------------------------
}

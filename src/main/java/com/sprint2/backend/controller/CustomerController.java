package com.sprint2.backend.controller;

import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.model.MessageDTO;
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

    @GetMapping("/get-customer-detail/{accountId}")
    public ResponseEntity<?> getCustomerDetail(@PathVariable Long accountId) {
        Customer customer = null;
        if (accountId != null) {
            customer = this.customerService.findByAccountId(accountId);
        }
        return customer != null ? ResponseEntity.ok(customer) : ResponseEntity.ok(new MessageDTO("not found"));
    }

    // --------------------Vinh end -------------------------
}

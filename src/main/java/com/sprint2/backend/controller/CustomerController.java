package com.sprint2.backend.controller;

import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.model.CustomerDTO;
import com.sprint2.backend.services.customer.CustomerService;
import javafx.util.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Long id){
        return new ResponseEntity<>(this.customerService.findByID(id), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateCustomer(@Validated @RequestBody CustomerDTO customerDTO, BindingResult bindingResult){
        System.out.println(customerDTO.getId());
        Customer customer = this.customerService.findByID(customerDTO.getId());
        customer.setAddress(customerDTO.getAddress());
        customer.setFullName(customerDTO.getFullName());
        customer.setGender(customerDTO.getGender());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        customer.setIdentityNumber(customerDTO.getIdentityNumber());
        customer.setImageAvatar(customerDTO.getImageAvatar());
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>("khánh ml",HttpStatus.BAD_REQUEST);
        }
        this.customerService.updateCustomer(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.sprint2.backend.controller;

import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.model.CustomerDTO;
import com.sprint2.backend.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    // ---------------- Hoàng begin ----------------------
    @Autowired
    private CustomerService customerService;

    //Delete customer by id
    @DeleteMapping("deleteCustomer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //get customer by id
    @GetMapping("/findCustomerById/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable long id) {
        return new ResponseEntity<>(customerService.findByID(id), HttpStatus.OK);
    }

    //edit customer
    @PutMapping("/editCustomer/{id}")
    public ResponseEntity<Void> editCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable long id) {
        Customer customer = customerService.findByID(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            customer.setCustomerCode(customerDTO.getCustomerCode());
            customer.setFullName(customerDTO.getFullName());
            customer.setBirthday(customerDTO.getBirthday());
            if (customerDTO.getGender().equals("Nam")) {
                customer.setGender(true);
            } else {
                customer.setGender(false);
            }
            customer.setIdentityNumber(customerDTO.getIdentityNumber());
            customer.setPhone(customerDTO.getPhone());
            customer.setEmail(customerDTO.getEmail());
            customer.setAddress(customerDTO.getAddress());
            customerService.save(customer);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    // ---------------- Hoàng end ------------------------
}

package com.sprint2.backend.controller;

import com.sprint2.backend.entity.CarType;
import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.entity.MemberCardType;
import com.sprint2.backend.model.CustomerDTO;
import com.sprint2.backend.repository.MemberCardRepository;
import com.sprint2.backend.services.CarType.CarTypeService;
import com.sprint2.backend.services.car.CarService;
import com.sprint2.backend.services.customer.CustomerService;
import com.sprint2.backend.services.member_card.MemberCardService;
import com.sprint2.backend.services.member_card_type.MemberCardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    CarService carService;
    @Autowired
    CarTypeService carTypeService;
    @Autowired
    MemberCardTypeService memberCardTypeService;
    @Autowired
    MemberCardService memberCardService;
    @GetMapping("/getCustomer/{input}/{key}")
    public List<Customer> getCustomer(
            @RequestParam(defaultValue = "0") int page,
            @PathVariable String key,
            @PathVariable String input) {
        Pageable pageable = PageRequest.of(page, 5);
        return customerService.search(input,key, pageable);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> customerList = this.customerService.findAll();
            return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK);
//        }
    }
    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        return new ResponseEntity<Customer>(customerService.findByID(id),HttpStatus.OK);
    }
    @PostMapping("/addNewCustomer")
    public ResponseEntity<Void> addCustomer(@RequestBody CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            customerService.saveCustomer(customerDTO);
            carService.saveCar(customerDTO);
            memberCardService.saveMemberCard(customerDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @GetMapping("/finAllCarType")
    public ResponseEntity<List<CarType>> getCarTypeList(){
        List<CarType> carTypeList = carTypeService.findAll();
        return new ResponseEntity<>(carTypeList, HttpStatus.OK);
    }
    @GetMapping("/findAllMemberType")
    public ResponseEntity<List<MemberCardType>> getMemberCardList(){
        List<MemberCardType> memberCardTypeList = memberCardTypeService.findAll();
        return new ResponseEntity<>(memberCardTypeList, HttpStatus.OK);
    }
}

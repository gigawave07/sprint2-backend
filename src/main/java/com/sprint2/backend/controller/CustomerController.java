package com.sprint2.backend.controller;

import com.sprint2.backend.entity.*;
import com.sprint2.backend.model.CustomerDTO;
import com.sprint2.backend.services.car.CarService;
import com.sprint2.backend.services.customer.CustomerService;
import com.sprint2.backend.services.mai_htq.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.model.MessageDTO;
import com.sprint2.backend.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    private CarService carService;

    @Autowired
    private ParkingSlotService parkingSlotService;

    /**
     * nguyen quoc khanh
     *
     * @param id
     * @return get customer by account id
     */
    @GetMapping("/find-customer-by-accountId/{id}")
    public ResponseEntity<Customer> getCustomerByAccountId(@PathVariable Long id) {
        Customer customer = this.customerService.findCustomerByAppAccountId(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    //-- Đin ---

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
    @GetMapping({"/list-entry-log/{accountId}/{pageable}","/list-entry-log/{accountId}/{pageable}/{plateNumber}"})
    public ResponseEntity<Page<ListEntryLogDTO>> getListEntryLog(@PathVariable Long accountId, @PathVariable int pageable,
                                                                 @PathVariable Optional<String> plateNumber) {
        return new ResponseEntity<>(this.customerService.findListEntryLog(accountId, pageable, plateNumber), HttpStatus.OK);
    }
    // End
    // --------------------Vinh begin -----------------------

    @GetMapping("/get-customer-detail/{accountId}")
    public ResponseEntity<?> getCustomerDetail(@PathVariable Long accountId) {
        Customer customer = null;
        if (accountId != null) {
            customer = this.customerService.findByAccountId(accountId);
        }
        return customer != null ? ResponseEntity.ok(customer) : ResponseEntity.ok(new MessageDTO("not found"));
    }

    // --------------------Vinh end -------------------------

    // ---------------- Hoàng begin ----------------------

    //Delete customer by id
    @GetMapping("/prepare-delete-customer/{customerId}")
    public ResponseEntity<Void> prepareDeleteCustomer(@PathVariable Long customerId) {
        if (customerId != null) {
            Customer customer = this.customerService.findByID(customerId);
            if (customer != null) {
                List<Car> carList = this.carService.getListCar(customerId);
                customer.setAppAccount(null);
                ParkingSlot parkingSlot;
                this.customerService.save(customer);
                for (Car car : carList) {
                    car.setCustomer(null);
                    this.carService.save(car);
                    parkingSlot = this.parkingSlotService.findByCar(car);
                    parkingSlot.setReserved(false);
                    parkingSlot.setStatus(false);
                    this.parkingSlotService.save(parkingSlot);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete-customer/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
        this.customerService.deleteByID(customerId);
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
            customer.setFullName(customerDTO.getFullName().trim());
            customer.setBirthday(customerDTO.getBirthday());
            customer.setGender(customerDTO.getGender());
            customer.setIdentityNumber(customerDTO.getIdentityNumber().trim());
            customer.setPhone(customerDTO.getPhone().trim());
            customer.setEmail(customerDTO.getEmail().trim());
            customer.setAddress(customerDTO.getAddress().trim());
            customerService.save(customer);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    // ---------------- Hoàng end ------------------------
}

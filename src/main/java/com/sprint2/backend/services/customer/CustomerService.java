package com.sprint2.backend.services.customer;

import java.util.List;

import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.model.CustomerDTO;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    //Ngan's tasks
    List<Customer> findAll();
    Customer findByID(Long id);
    void saveCustomer (CustomerDTO customerDTO);
    void update (CustomerDTO customerDTO);
    void remove (Long id);
    List<Customer> search(String input, String key, Pageable pageable);
    Customer findCustomerByCustomerCode(String customerCode);
    //End Ngan's tasks
}

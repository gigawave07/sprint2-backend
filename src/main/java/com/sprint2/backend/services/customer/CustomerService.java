package com.sprint2.backend.services.customer;

import java.util.List;

import com.sprint2.backend.entity.Customer;

public interface CustomerService {

    void save(Customer customer);

    List<Customer> findAll();

    Customer findByID(Long id);

    String delete(long id);
}

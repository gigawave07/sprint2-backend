package com.sprint2.backend.services.customer;

import java.util.List;

import com.sprint2.backend.entity.Customer;

public interface CustomerService {
    List<Customer> findAll();

    Customer findByID(Long id);

    // ----------------------- Vinh Begin -------------------------

    Customer findByAccountId(Long id);

    // ----------------------- Vinh End -------------------------
}

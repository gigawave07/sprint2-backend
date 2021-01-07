package com.sprint2.backend.services.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer findByID(Long id) {
        return this.customerRepository.findById(id).orElse(null);
    }

    // ----------------------- Vinh Begin -------------------------
    @Override
    public Customer findByAccountId(Long id) {
        return this.customerRepository.findByAppAccountId(id);
    }
    // ----------------------- Vinh End ---------------------------
}

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
    public void save(Customer customer) {
        this.customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer findByID(Long id) {
        return this.customerRepository.findById(id).orElse(null);
    }

    @Override
    public String delete(long id) {
        Customer customer = findByID(id);
        customer.setAppAccount(null);
        try {
            this.customerRepository.deleteById(id);
        } catch (RuntimeException runtime) {
            return "Failed";
        }
        return "Succeed";
    }

}

package com.sprint2.backend.services.customer_ngan;

import java.util.List;

import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.model.NganCustomerDTO;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    //Ngan's tasks
    List<Customer> findAll();
    Customer findByID(Long id);
    void saveCustomer (NganCustomerDTO nganCustomerDTO);
    void update (NganCustomerDTO nganCustomerDTO);
    void remove (Long id);
    List<Customer> search(String input, String key, Pageable pageable);
    Customer findCustomerByCustomerCode(String customerCode);
    //End Ngan's tasks
}

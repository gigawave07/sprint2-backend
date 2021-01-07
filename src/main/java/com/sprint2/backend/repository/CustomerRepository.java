package com.sprint2.backend.repository;

import com.sprint2.backend.model.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    //Ngan's tasks
    @Query(value = "select * from customer where customer.full_name like %?1%", nativeQuery = true)
    List<Customer> getCustomersByName (String string);

    @Query(value = "select * from customer where customer.customer_code like %?1%", nativeQuery = true)
    List<Customer> getCustomersByCustomerCode (String string);

    @Query(value = "select * from customer where customer.identity_number like %?1%", nativeQuery = true)
    List<Customer> getCustomersByIdNumber (String string);

    @Query(value = "select * from customer where customer.email like %?1%", nativeQuery = true)
    List<Customer> getCustomersByEmail (String string);

    Customer findCustomerByCustomerCode(String customerCode);
    //End Ngan's tasks

}

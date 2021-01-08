package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
     * nguyen quoc khanh
     * @param id
     * @return
     */
    @Query(value = "select * from customer where app_account_id = ?",nativeQuery = true)
    Customer findCustomerByAppAccountId(Long id);
}

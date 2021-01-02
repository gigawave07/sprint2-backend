package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Thống kê số lượng khách hàng
    @Query(nativeQuery = true, value = "select count(*) as total_customer from project2_parking_management.customer")
    Long getTotalCustomer();
}

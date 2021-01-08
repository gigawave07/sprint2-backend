package com.sprint2.backend.repository.lanh_nqn;

import com.sprint2.backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByFullName(String fullName);
}

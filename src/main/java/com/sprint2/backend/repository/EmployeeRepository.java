package com.sprint2.backend.repository;

import com.sprint2.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByFullNameContaining(String fullName);

    Employee findByPositionContaining(String position);
}

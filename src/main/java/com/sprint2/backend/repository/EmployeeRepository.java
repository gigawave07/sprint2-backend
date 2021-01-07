package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findEmployeeByFullNameContaining(String fullName);
    List <Employee> findEmployeeByPositionContaining(String position);
    List <Employee> findEmployeeByIdContaining(Long id);

//    List<Employee> findAllByStatusTrueAndFullNameContainingOrPositionContaining(String fullName, String position);

    // ----------------------- Validate ton tai ---------------------------
    List<Employee> findEmployeeByEmployeeCode (String employeeCode);
//    List<Employee> findAllByStatusTrue();
}

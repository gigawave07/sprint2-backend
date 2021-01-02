package com.sprint2.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint2.backend.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByFullNameContaining(String fullName);

    Employee findByPositionContaining(String position);

    // Thống kê số lượng nhân viên
    @Query(nativeQuery = true, value = "select count(*) as total_employee from project2_parking_management.employee")
    Long getTotalEmployee();
}

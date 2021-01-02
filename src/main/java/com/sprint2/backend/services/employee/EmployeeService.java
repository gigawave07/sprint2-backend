package com.sprint2.backend.services.employee;

import java.util.List;

import com.sprint2.backend.entity.Employee;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findByID(Long id);

    void save(Employee employee);

    void update(Employee employee);

    void deleteByID(Long id);

    Employee findByFullName(String fullName);

    Employee findByPosition(String position);

    // Thống kê số lượng nhân viên
    Long getTotalEmployee();
}

package com.sprint2.backend.services.employee;

import java.util.List;

import com.sprint2.backend.entity.AppAccount;
import com.sprint2.backend.entity.AppRole;
import com.sprint2.backend.entity.Employee;

public interface EmployeeService {
    List<Employee> findAllEmployee();

    Employee findEmployeeById(Long id);

    void saveEmployee(Employee employee);

    void editEmployee(Employee employee);

    void deleteEmployee(Long id);


    //------------------- Validate ton tai --------------------------
    List<Employee> findEmployeeByCode(String code);

    //------------------- Account - Role --------------------------
    void saveAccount(AppAccount appAccount);

    AppAccount findByNameAppAccount(String appAccount);

    AppRole findByNameAppRole(String appRole);

    List<AppRole> findAllByRole();
    //-------------------------- Search -------------------------------
    List<Employee> findEmployeeByFullNameContaining (String fullName);

    List<Employee> findEmployeeByIdContaining (Long id);

    List<Employee> findEmployeeByPositionContaining (String position);
}

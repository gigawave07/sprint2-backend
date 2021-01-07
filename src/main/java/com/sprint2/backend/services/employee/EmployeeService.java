package com.sprint2.backend.services.employee;

import java.util.List;

import com.sprint2.backend.entity.AppAccount;
import com.sprint2.backend.entity.AppRole;
import com.sprint2.backend.entity.Employee;
import com.sprint2.backend.model.EmployeeDTO;

public interface EmployeeService {
    List<Employee> findAllEmployee();

    Employee findEmployeeById(Long id);

    void saveEmployee(EmployeeDTO employeeDTO);

    void editEmployee(Employee employee);

    void deleteEmployee(Long id);


    List<AppRole> findAllByRole();
    //-------------------------- Search -------------------------------
    List<Employee> findEmployeeByFullNameContaining (String fullName);

    List<Employee> findEmployeeByIdContaining (Long id);

    List<Employee> findEmployeeByPositionContaining (String position);

}

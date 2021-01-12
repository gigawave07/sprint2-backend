package com.sprint2.backend.services.employee;

import java.util.List;

import com.sprint2.backend.entity.AppAccount;
import com.sprint2.backend.entity.AppRole;
import com.sprint2.backend.entity.Employee;
import com.sprint2.backend.model.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findByID(Long id);

    void save(Employee employee);

    void update(Employee employee);

    void deleteByID(Long id);

    Employee findByFullName(String fullName);

    Employee findByPosition(String position);


    /**
     * Nguyen Quang Danh
     *
     * @return Thống kê số lượng nhân viên
     */
    Long getTotalEmployee();
//    Đạt
    List<Employee> findAllEmployee();

    Employee findEmployeeById(Long id);

    void saveEmployee(EmployeeDTO employeeDTO);

    void editEmployee(Employee employee);

    void deleteEmployee(Long id);


    List<AppRole> findAllByRole();
    //-------------------------- Search -------------------------------


    List<Employee> searchEmployee (String inputSearch);

}

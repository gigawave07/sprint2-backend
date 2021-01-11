package com.sprint2.backend.controller;

import com.sprint2.backend.entity.AppAccount;
import com.sprint2.backend.entity.AppRole;
import com.sprint2.backend.entity.Employee;
import com.sprint2.backend.model.EmployeeDTO;
import com.sprint2.backend.services.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    

    // Create by: Đạt _ Get List Employee
    @GetMapping("/list")
    public ResponseEntity<List<Employee>> getListEmployee() {
        List<Employee> listEmployee = this.employeeService.findAllEmployee();
        return new ResponseEntity<>(listEmployee, HttpStatus.OK);
    }

    // Create by: Đạt _ Get List Role
    @GetMapping("/listRole")
    public ResponseEntity<List<String>> getListRole() {
        List<AppRole> listRole = this.employeeService.findAllByRole();
        List<String> listRoleDTO = new ArrayList<>();
        for (AppRole appRole : listRole) {
            listRoleDTO.add(appRole.getRoleName());
        }
        return new ResponseEntity<>(listRoleDTO, HttpStatus.OK);
    }

    // Create by: Đạt _ Find Employee By ID
    @GetMapping("/findEmployeeById/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = this.employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // Create by: Đạt _ Delete Employee By ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable Long id) {
        Employee employee = this.employeeService.findEmployeeById(id);
        employee.getAppAccount().setAppRole(null);
        this.employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // Create by: Đạt _ Create Employee
    @PostMapping("/createNew")
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody EmployeeDTO employeeDTO) {
        this.employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // Create by: Đạt _ Edit Employee
    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> editUser(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.findEmployeeById(id);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            employee.setFullName(employeeDTO.getFullName().trim());
            employee.setAddress(employeeDTO.getAddress().trim());
            employee.setBirthday(employeeDTO.getBirthday());
            employee.setGender(employeeDTO.getGender());
            employee.setEmail(employeeDTO.getEmail());
            employee.setPhoneNumber(employeeDTO.getPhoneNumber());
            employee.setPosition(employeeDTO.getPosition());
            employeeService.editEmployee(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    //------------------------------ Search Employee ----------------------------------
    // Create by: Đạt _ Search Employee by full name
//    @GetMapping("/searchFullName/{fullName}")
//    public ResponseEntity<List<Employee>> searchFullNameEmployee(@PathVariable String fullName) {
//        List<Employee> listEmployee = this.employeeService.findEmployeeByFullNameContaining(fullName);
//        return new ResponseEntity<>(listEmployee, HttpStatus.OK);
//    }
//
//    // Create by: Đạt _ Search Employee by phone number
//    @GetMapping("/searchId/{id}")
//    public ResponseEntity<List<Employee>> searchIdEmployee(@PathVariable Long id) {
//        List<Employee> listEmployee = this.employeeService.findEmployeeByIdContaining(id);
//        return new ResponseEntity<>(listEmployee, HttpStatus.OK);
//    }
//
//    // Create by: Đạt _ Search Employee by email
//    @GetMapping("/searchPosition/{position}")
//    public ResponseEntity<List<Employee>> searchPositionEmployee(@PathVariable String position) {
//        List<Employee> listEmployee = this.employeeService.findEmployeeByPositionContaining(position);
//        return new ResponseEntity<>(listEmployee, HttpStatus.OK);
//    }

    @GetMapping("/inputSearch")
    public ResponseEntity<List<Employee>> searchUsers(@RequestParam("valueSearch") String inputSearch) {
        List<Employee> employeeList = employeeService.searchEmployee(inputSearch);
        if (employeeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }
}

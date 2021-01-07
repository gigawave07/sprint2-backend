package com.sprint2.backend.services.employee;

import com.sprint2.backend.entity.AppAccount;
import com.sprint2.backend.entity.AppRole;
import com.sprint2.backend.model.EmployeeDTO;
import com.sprint2.backend.repository.AppAccountRepository;
import com.sprint2.backend.repository.AppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.sprint2.backend.entity.Employee;
import com.sprint2.backend.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AppAccountRepository appAccountRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;


    @Override
    public List<Employee> findAllEmployee() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return this.employeeRepository.findById(id).orElse(null);

    }

    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) {
        System.out.println(employeeDTO.getPassword());
        AppAccount appAccount = new AppAccount();
        appAccount.setUsername(employeeDTO.getEmail());
        appAccount.setPassword(employeeDTO.getPassword());
        appAccount.setAppRole(this.appRoleRepository.findById(2L).orElse(null));
        this.appAccountRepository.save(appAccount);
        Employee employee = new Employee();
//        employee.setEmployeeCode(employeeDTO.getEmployeeCode());
        employee.setGender(employeeDTO.getGender());
        employee.setFullName(employeeDTO.getFullName());
        employee.setBirthday(employeeDTO.getBirthday());
        employee.setAddress(employeeDTO.getAddress());
        employee.setPosition(employeeDTO.getPosition());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setAppAccount(appAccount);
        this.employeeRepository.save(employee);
    }

    @Override
    public void editEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
       Employee employee = this.employeeRepository.findById(id).orElse(null);
       AppAccount appAccount = employee.getAppAccount();
       this.appAccountRepository.delete(appAccount);
//       if (employee != null){
//           employee.setStatus(false);
//           this.employeeRepository.save(employee);
//       }
    }

//    @Override
//    public List<Employee> findEmployeeByCode(String code) {
//        return this.employeeRepository.findEmployeeByEmployeeCode(code);
//    }

    @Override
    public void saveAccount(AppAccount appAccount) {
        this.appAccountRepository.save(appAccount);
    }

    @Override
    public AppAccount findByNameAppAccount(String appAccount) {
        return null;
    }

    @Override
    public AppRole findByNameAppRole(String appRole) {
        return null;
    }

    @Override
    public List<AppRole> findAllByRole() {
        try {
            return appRoleRepository.findAll();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public List<Employee> findEmployeeByFullNameContaining(String fullName) {
        return this.employeeRepository.findEmployeeByFullNameContaining(fullName);
    }

    @Override
    public List<Employee> findEmployeeByIdContaining(Long id) {
        return this.employeeRepository.findEmployeeByIdContaining(id);
    }

    @Override
    public List<Employee> findEmployeeByPositionContaining(String position) {
        return this.employeeRepository.findEmployeeByPositionContaining(position);
    }

//    @Override
//    public List<Employee> findAllEmployeeByStatus() {
//        return this.employeeRepository.findAllByStatusTrue();
//    }
//
//    @Override
//    public List<Employee> searchEmployee(String fullName, String position) {
//        return this.employeeRepository.findAllByStatusTrueAndFullNameContainingOrPositionContaining(fullName, position);
//    }


}

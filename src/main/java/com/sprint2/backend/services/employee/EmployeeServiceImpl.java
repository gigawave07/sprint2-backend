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
        employee.setGender(employeeDTO.getGender());
        employee.setFullName(employeeDTO.getFullName().trim());
        employee.setBirthday(employeeDTO.getBirthday());
        employee.setAddress(employeeDTO.getAddress().trim());
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
    public List<Employee> searchEmployee(String inputSearch) {
        return employeeRepository.findAllByFullNameContainingOrPositionContaining(inputSearch, inputSearch);
    }




}

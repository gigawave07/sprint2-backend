package com.sprint2.backend.services.employee;

import com.sprint2.backend.entity.Employee;
import com.sprint2.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee findByID(Long id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public void update(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public void deleteByID(Long id) {
        this.employeeRepository.deleteById(id);
    }

    @Override
    public Employee findByFullName(String fullName) {
        return this.employeeRepository.findByFullNameContaining(fullName);
    }

    @Override
    public Employee findByPosition(String position) {
        return this.employeeRepository.findByPositionContaining(position);
    }

    // Thống kê số lượng nhân viên
    @Override
    public Long getTotalEmployee() {
        return this.employeeRepository.getTotalEmployee();
    }
}

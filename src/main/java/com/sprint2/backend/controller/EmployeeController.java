package com.sprint2.backend.controller;

import com.sprint2.backend.entity.Employee;
import com.sprint2.backend.model.MessageDTO;
import com.sprint2.backend.services.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // ------------------------------------ Vinh Begin -------------------------------------
    @GetMapping("/getEmployeeInformation/{employeeId}")
    public ResponseEntity<?> getEmployeeInformation(@PathVariable Long employeeId){
        Employee employee = null;
        if (employeeId != null) {
            employee = this.employeeService.findByID(employeeId);
        }
        return (employee != null) ? ResponseEntity.ok(employee) : ResponseEntity.ok(new MessageDTO("not found"));
    }
    // ------------------------------------ Vinh Begin -------------------------------------
}

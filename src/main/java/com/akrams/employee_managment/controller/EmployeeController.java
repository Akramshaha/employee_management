package com.akrams.employee_managment.controller;

import com.akrams.employee_managment.dto.EmployeeDTO;
import com.akrams.employee_managment.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllEmployees(){
        return new ResponseEntity<List<EmployeeDTO>>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewEmployee(@RequestBody EmployeeDTO employeeDTO){
        LocalDateTime localDateTime = LocalDateTime.now();
        employeeDTO.setCreatedAt(localDateTime);
        return new ResponseEntity<EmployeeDTO>(employeeService.addNewEmployee(employeeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        LocalDateTime localDateTime = LocalDateTime.now();
        employeeDTO.setUpdatedAt(localDateTime);
        return new ResponseEntity<EmployeeDTO>(employeeService.addNewEmployee(employeeDTO), HttpStatus.OK);
    }
}

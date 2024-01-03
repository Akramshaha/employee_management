package com.akrams.employee_managment.controller;

import com.akrams.employee_managment.dto.EmployeeDTO;
import com.akrams.employee_managment.dto.EmployeeSearchDTO;
import com.akrams.employee_managment.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllEmployees(){
        log.info("In the ALL Mapping");
        return new ResponseEntity<List<EmployeeDTO>>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findEmployeeByCriteria(@RequestParam("name") String name, @RequestParam("year") Integer year, @RequestParam(value = "ageStart", defaultValue = "18") Integer ageStartLimit, @RequestParam(value = "ageEnd", defaultValue = "100") Integer ageEndLimit){
        return new ResponseEntity<List<EmployeeDTO>>(employeeService.getEmployeesByCriteria( name, year, ageStartLimit, ageEndLimit), HttpStatus.OK);
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

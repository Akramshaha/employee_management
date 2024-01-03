package com.akrams.employee_managment.services;

import com.akrams.employee_managment.dto.EmployeeDTO;
import com.akrams.employee_managment.model.Employee;

import java.util.List;

public interface EmployeeService {

    public List<EmployeeDTO> getAllEmployees();

    EmployeeDTO addNewEmployee(EmployeeDTO employeeDTO);
}

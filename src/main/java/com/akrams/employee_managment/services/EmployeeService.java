package com.akrams.employee_managment.services;

import com.akrams.employee_managment.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    public List<EmployeeDTO> getAllEmployees();

    EmployeeDTO addNewEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getEmployeesByCriteria(String name, Integer year, Integer ageStartLimit, Integer ageEndLimit);
}

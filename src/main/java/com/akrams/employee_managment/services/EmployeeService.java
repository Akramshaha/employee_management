package com.akrams.employee_managment.services;

import com.akrams.employee_managment.dto.EmployeeDTO;
import com.akrams.employee_managment.dto.EmployeeFormDTO;
import com.akrams.employee_managment.model.Department;

import java.util.List;

public interface EmployeeService {

    public List<EmployeeDTO> getAllEmployees();

    EmployeeDTO addNewEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getEmployeesByCriteria(String name, Integer year, Integer ageStartLimit, Integer ageEndLimit);

    List<Department> getAllDept();

    EmployeeFormDTO getAllEmployeeFormData();

    EmployeeDTO getEditEmployee(String id);
}

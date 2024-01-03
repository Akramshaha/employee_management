package com.akrams.employee_managment.services.impl;

import com.akrams.employee_managment.dto.EmployeeDTO;
import com.akrams.employee_managment.model.Employee;
import com.akrams.employee_managment.repository.EmployeeRepository;
import com.akrams.employee_managment.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employeeDTOList = employeeRepository.findAll().stream().map(
                (e) -> modelMapper.map(e, EmployeeDTO.class)
        ).collect(Collectors.toList());

        return employeeDTOList;
    }

    @Override
    public EmployeeDTO addNewEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        return modelMapper.map(employeeRepository.save(employee), EmployeeDTO.class);
    }
}

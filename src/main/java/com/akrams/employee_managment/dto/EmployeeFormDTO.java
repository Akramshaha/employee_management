package com.akrams.employee_managment.dto;

import com.akrams.employee_managment.enums.Designation;
import com.akrams.employee_managment.enums.Gender;
import com.akrams.employee_managment.enums.MaritalStatus;
import com.akrams.employee_managment.model.Department;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class EmployeeFormDTO {

    private List<Department> departmentList;
    private List<String> genderList = Stream.of(Gender.values()).map(Gender::name).collect(Collectors.toList());
    private List<String> designationList = Stream.of(Designation.values()).map(Designation::name).collect(Collectors.toList());
    private List<String> marStatusList = Stream.of(MaritalStatus.values()).map(MaritalStatus::name).collect(Collectors.toList());
}

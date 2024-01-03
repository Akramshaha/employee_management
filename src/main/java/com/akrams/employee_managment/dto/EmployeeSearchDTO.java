package com.akrams.employee_managment.dto;

import lombok.Data;

@Data
public class EmployeeSearchDTO {

    private String employeeId;
    private Integer fromRange;
    private Integer toRange;
    private Integer year;
    private String name;
}

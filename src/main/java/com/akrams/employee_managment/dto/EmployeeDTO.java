package com.akrams.employee_managment.dto;

import com.akrams.employee_managment.enums.Designation;
import com.akrams.employee_managment.enums.Gender;
import com.akrams.employee_managment.enums.MaritalStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class EmployeeDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private int age;

    @Enumerated(EnumType.STRING)
    MaritalStatus maritalStatus;
    private LocalDate dateOfBirth;
    private Integer departmentId;

    @Enumerated(EnumType.STRING)
    Designation designation;

    @Enumerated(EnumType.STRING)
    Gender gender;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

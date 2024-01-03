package com.akrams.employee_managment.model;

import com.akrams.employee_managment.enums.Designation;
import com.akrams.employee_managment.enums.Gender;
import com.akrams.employee_managment.enums.MaritalStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
@Data
public class Employee {

    @Id
    @GeneratedValue(generator = "employee-id-sequence")
    @GenericGenerator(
            name = "employee-id-sequence",
            strategy = "com.akrams.employee_managment.utils.EmployeeIdSequence"
    )
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private int age;

    @Enumerated(EnumType.STRING)
    MaritalStatus maritalStatus;

    @Column(name = "dob")
    private LocalDate dateOfBirth;

    @ManyToOne
    private Department department;

    @Enumerated(EnumType.STRING)
    Designation designation;

    @Enumerated(EnumType.STRING)
    Gender gender;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

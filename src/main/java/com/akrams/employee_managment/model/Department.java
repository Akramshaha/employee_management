package com.akrams.employee_managment.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "department")
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String deptName;
    private String deptDesc;
    @OneToMany
    private List<Employee> employees;
}

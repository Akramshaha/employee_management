package com.akrams.employee_managment.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String token;
}

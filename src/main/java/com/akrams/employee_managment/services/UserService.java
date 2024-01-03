package com.akrams.employee_managment.services;

import com.akrams.employee_managment.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    public User save(User newUser);
    public UserDetailsService userDetailsService();
}

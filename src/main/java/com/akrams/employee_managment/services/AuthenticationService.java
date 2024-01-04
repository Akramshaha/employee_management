package com.akrams.employee_managment.services;

import com.akrams.employee_managment.dto.SignInRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    ResponseEntity<?> signin(SignInRequest request);
    boolean validateToken(String jwt, UserDetails userDetails);
}

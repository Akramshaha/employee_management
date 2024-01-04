package com.akrams.employee_managment.controller;

import com.akrams.employee_managment.dto.SignInRequest;
import com.akrams.employee_managment.model.User;
import com.akrams.employee_managment.services.AuthenticationService;
import com.akrams.employee_managment.services.impl.AuthenticationServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final Logger logger = LoggerFactory.getLogger( AuthenticationController.class);

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignInRequest request) {
        ResponseEntity<?> response = authenticationService.signin(request);
        return response;
    }

    @GetMapping("/validateJWT")
    public ResponseEntity<?> validateJWT(@RequestParam("token") String token, @AuthenticationPrincipal User user){
        try {
            Boolean isTokenValid = authenticationService.validateToken(token,user);
            return ResponseEntity.ok(isTokenValid);
        }catch (ExpiredJwtException e){
            return ResponseEntity.ok(false);
        }
    }

}
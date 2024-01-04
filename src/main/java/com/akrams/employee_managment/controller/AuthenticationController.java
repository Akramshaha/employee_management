package com.akrams.employee_managment.controller;

import com.akrams.employee_managment.dto.SignInRequest;
import com.akrams.employee_managment.model.User;
import com.akrams.employee_managment.services.impl.AuthenticationServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignInRequest request) {
        return authenticationService.signin(request);

    }

    @GetMapping("/validateJWT")
    public ResponseEntity<?> validateJWT(@RequestParam String token, @AuthenticationPrincipal User user){
        try {
            Boolean isTokenValid = authenticationService.validateToken(token,user);
            return ResponseEntity.ok(isTokenValid);
        }catch (ExpiredJwtException e){
            return ResponseEntity.ok(false);
        }
    }

}
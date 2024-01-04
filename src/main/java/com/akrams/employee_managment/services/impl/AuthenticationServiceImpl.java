package com.akrams.employee_managment.services.impl;

import com.akrams.employee_managment.dto.SignInRequest;
import com.akrams.employee_managment.model.User;
import com.akrams.employee_managment.repository.UserRepository;
import com.akrams.employee_managment.services.AuthenticationService;
import com.akrams.employee_managment.services.JwtService;
import com.akrams.employee_managment.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> signin(SignInRequest request) {
        try {
            Authentication authenticate =authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            User user = (User) authenticate.getPrincipal();
//                    .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
            String jwt = jwtService.generateToken(user);
            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwt
                    )
                    .body(user);
        }catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
//        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public boolean validateToken(String jwt, UserDetails userDetails){
        return jwtService.isTokenValid(jwt, userDetails);
    }

}

package com.akrams.employee_managment.dto;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Data
@Builder
@Getter
@Setter
public class SignInRequest {
  String email;
  String password;
}

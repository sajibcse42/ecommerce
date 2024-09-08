package com.example.EcommerceApplication.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
public class RegisterUserDto {
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String password;
}

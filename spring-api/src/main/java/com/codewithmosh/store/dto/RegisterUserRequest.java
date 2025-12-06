package com.codewithmosh.store.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {

    @NotBlank(message = "Name is Required")
    @Size(max = 255, message = "Name Must be less the 255 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email Should valid")
    private String email;

    @NotBlank(message = "Password is Required")
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;
}

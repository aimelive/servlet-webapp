package com.aimelive.mywebapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateFamousDto {
    @NotBlank(message = "Star name cannot be blank")
    private String starName;

    @NotBlank(message = "Real name cannot be blank")
    private String realName;

    @NotBlank(message = "Famous bio cannot be blank")
    private String bio;

    @Positive(message = "Age must be a positive number")
    private int age;
}

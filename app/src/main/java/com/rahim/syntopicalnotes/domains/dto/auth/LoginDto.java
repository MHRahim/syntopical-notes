package com.rahim.syntopicalnotes.domains.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDto(
    @NotBlank(message = "Email is required.")
    @Email(message = "Please provide a valid email address.")
    @Size(max = 100, message = "Email is too long.")
    String email, 

    @NotBlank(message = "Password is required.")
    @Size(min = 3, max = 100, message = "Password must be between 3 - 100 characters long.")
    String password
) {
}

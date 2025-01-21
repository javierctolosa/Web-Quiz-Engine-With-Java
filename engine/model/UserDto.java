package engine.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserDto(
                @NotBlank(message = "The 'email' field must not be blank.")
                @Pattern(regexp = "^[\\w+.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}",
                        message = "The 'email' field must contain a valid email address.")
                String email,
                @NotBlank(message = "The 'password' field must not be blank.")
                @Size(min = 5, message = "The 'password' field must have at least 5 characters.")
                String password) {}

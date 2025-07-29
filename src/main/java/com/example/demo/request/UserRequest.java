package com.example.demo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    @NotBlank(message = "Name must not be blank")
    @Schema(description = "Full name of the user", example = "John Doe")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email must not be blank")
    @Schema(description = "Email address", example = "john@example.com")
    private String email;

    @NotNull(message = "Date of birth is required")
    @Schema(description = "Date of birth (yyyy-MM-dd)", example = "1990-01-01")
    private java.time.LocalDate dob;
}

package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.response.ApiResponse;
import com.example.demo.request.UserRequest;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create a new user")
    public ApiResponse<User> createUser(
            @Valid @RequestBody UserRequest request
    ) {
        return ApiResponse.success(userService.create(request));
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public ApiResponse<List<User>> getAllUsers() {
        return ApiResponse.success(userService.getAll());
    }

    @GetMapping("/echo")
    @Operation(summary = "Demo validate query param")
    public ApiResponse<String> echoParam(
            @Parameter(description = "Age must be >= 18")
            @RequestParam @Min(value = 18, message = "Age must be at least 18") int age
    ) {
        return ApiResponse.success("Your age is valid: " + age);
    }
}
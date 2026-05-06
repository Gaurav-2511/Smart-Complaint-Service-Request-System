package com.smartcomplaint.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartcomplaint.dto.ApiResponse;
import com.smartcomplaint.dto.LoginRequest;
import com.smartcomplaint.dto.LoginResponse;
import com.smartcomplaint.dto.RegisterRequest;
import com.smartcomplaint.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<String>> registerUser(@Valid @RequestBody RegisterRequest request) {

		String message = userService.registerUser(request);

		ApiResponse<String> response = new ApiResponse<>(true, message, null);

		return new ResponseEntity<ApiResponse<String>>(response, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponse>> loginUser(@Valid @RequestBody LoginRequest request) {

		LoginResponse loginResponse = userService.loginUser(request);

		ApiResponse<LoginResponse> response = new ApiResponse<>(true, "Login successful", loginResponse);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
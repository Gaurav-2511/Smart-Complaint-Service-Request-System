package com.smartcomplaint.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartcomplaint.dto.ApiResponse;
import com.smartcomplaint.dto.LoginRequest;
import com.smartcomplaint.dto.LoginResponse;
import com.smartcomplaint.dto.RegisterRequest;
import com.smartcomplaint.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth/user")
public class UserController {

	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<String>> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
		String message = userService.registerUser(registerRequest);

		ApiResponse<String> response = new ApiResponse<String>(true, message, null);

		return new ResponseEntity<ApiResponse<String>>(response, HttpStatus.CREATED);
	}

	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
		LoginResponse loginUser = userService.loginUser(loginRequest);

		ApiResponse<LoginResponse> response = new ApiResponse<LoginResponse>(true, "Login success", loginUser);

		return new ResponseEntity<ApiResponse<LoginResponse>>(response, HttpStatus.OK);
	}

}

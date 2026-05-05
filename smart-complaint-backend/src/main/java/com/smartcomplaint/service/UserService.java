package com.smartcomplaint.service;

import com.smartcomplaint.dto.LoginRequest;
import com.smartcomplaint.dto.LoginResponse;
import com.smartcomplaint.dto.RegisterRequest;

public interface UserService {

	String registerUser(RegisterRequest request);
	
	LoginResponse loginUser(LoginRequest request);
}

package com.smartcomplaint.service.impl;

import org.springframework.stereotype.Service;

import com.smartcomplaint.dto.LoginRequest;
import com.smartcomplaint.dto.LoginResponse;
import com.smartcomplaint.dto.RegisterRequest;
import com.smartcomplaint.entity.User;
import com.smartcomplaint.enums.Role;
import com.smartcomplaint.repository.UserRepository;
import com.smartcomplaint.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public String registerUser(RegisterRequest request) {

		if (userRepository.existsByEmail(request.getEmail())) {
			throw new RuntimeException("Email already exists");
		}

		User user = new User();

		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setRole(Role.USER);

		userRepository.save(user);
		return "User registered successfully";
	}

	@Override
	public LoginResponse loginUser(LoginRequest request) {
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("Invalid email or password"));

		if (!user.getPassword().equals(request.getPassword())) {
			throw new RuntimeException("Invalid email or password");
		}

		return new LoginResponse(
				user.getId(),
				user.getName(),
				user.getEmail(),
				user.getRole().name(),
				"Login successful");
	}

}

package com.smartcomplaint.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smartcomplaint.dto.LoginRequest;
import com.smartcomplaint.dto.LoginResponse;
import com.smartcomplaint.dto.RegisterRequest;
import com.smartcomplaint.entity.User;
import com.smartcomplaint.enums.Role;
import com.smartcomplaint.exception.DuplicateResourceException;
import com.smartcomplaint.exception.InvalidCredentialsException;
import com.smartcomplaint.repository.UserRepository;
import com.smartcomplaint.security.JwtService;
import com.smartcomplaint.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	@Override
	public String registerUser(RegisterRequest request) {

		if (userRepository.existsByEmail(request.getEmail())) {
			throw new DuplicateResourceException("Email already registered");
		}

		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(Role.USER);

		userRepository.save(user);

		return "User registered successfully";
	}

	@Override
	public LoginResponse loginUser(LoginRequest request) {

		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

		boolean isPasswordMatch = passwordEncoder.matches(request.getPassword(), user.getPassword());

		if (!isPasswordMatch) {
			throw new InvalidCredentialsException("Invalid email or password");
		}

		String token = jwtService.generateToken(user);

		return new LoginResponse(token, user.getId(), user.getName(), user.getEmail(), user.getRole());
	}
}
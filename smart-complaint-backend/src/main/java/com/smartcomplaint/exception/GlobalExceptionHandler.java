package com.smartcomplaint.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.core.AuthenticationException;

import com.smartcomplaint.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleResourceNotFoundException(ResourceNotFoundException ex) {

		ApiResponse<String> response = new ApiResponse<>(false, ex.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ApiResponse<String>> handleDuplicateResourceException(DuplicateResourceException ex) {

		ApiResponse<String> response = new ApiResponse<>(false, ex.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ApiResponse<String>> handleInvalidCredentialsException(InvalidCredentialsException ex) {

		ApiResponse<String> response = new ApiResponse<>(false, ex.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(InvalidStatusException.class)
	public ResponseEntity<ApiResponse<String>> handleInvalidStatusException(InvalidStatusException ex) {

		ApiResponse<String> response = new ApiResponse<>(false, ex.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnauthorizedAccessException.class)
	public ResponseEntity<ApiResponse<String>> handleUnauthorizedAccessException(UnauthorizedAccessException ex) {

		ApiResponse<String> response = new ApiResponse<>(false, ex.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(
			MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});

		ApiResponse<Map<String, String>> response = new ApiResponse<>(false, "Validation failed", errors);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponse<String>> handleAccessDeniedException(AccessDeniedException ex) {

		ApiResponse<String> response = new ApiResponse<>(false,
				"Access denied. You do not have permission to access this API", null);

		return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ApiResponse<String>> handleAuthenticationException(AuthenticationException ex) {

		ApiResponse<String> response = new ApiResponse<>(false, "Authentication failed. Please login again", null);

		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiResponse<String>> handleRuntimeException(RuntimeException ex) {

		ApiResponse<String> response = new ApiResponse<>(false, ex.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<String>> handleGeneralException(Exception ex) {

		ApiResponse<String> response = new ApiResponse<>(false, "Something went wrong", null);

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
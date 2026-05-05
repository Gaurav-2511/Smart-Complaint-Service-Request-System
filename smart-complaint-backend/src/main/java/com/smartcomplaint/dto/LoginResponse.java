package com.smartcomplaint.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

	private Long userId;
	
	private String name;
	
	private String email;
	
	private String role;
	
	private String message;
}

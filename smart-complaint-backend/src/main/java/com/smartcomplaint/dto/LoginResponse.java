package com.smartcomplaint.dto;

import com.smartcomplaint.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private Long userId;
    private String name;
    private String email;
    private Role role;
}
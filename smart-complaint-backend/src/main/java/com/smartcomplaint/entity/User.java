package com.smartcomplaint.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	@Column(nullable = false, length = 100)
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Emali should be valid")
	@Column(nullable = false, unique = true, length = 100)
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 4, max = 100, message = "Password must be between 4 and 100 characters")
	@Column(nullable = false, length = 100)
	private String password;

	@NotBlank(message = "Role is required")
	@Pattern(regexp = "USER|ADMIN", message = "Role must be USER or ADMIN")
	@Column(nullable = false, length = 20)
	private String role;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Complaint> complaints = new ArrayList<>();

}
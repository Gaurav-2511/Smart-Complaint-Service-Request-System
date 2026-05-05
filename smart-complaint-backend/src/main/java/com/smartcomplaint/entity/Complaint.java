package com.smartcomplaint.entity;

import java.time.LocalDateTime;

import com.smartcomplaint.enums.ComplaintStatus;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "complaints")
public class Complaint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Title is required")
	@Size(min = 3, max = 150, message = "Title must be between 3 and 150 characters")
	@Column(nullable = false, length = 150)
	private String title;

	@NotBlank(message = "Description is required")
	@Size(min = 5, max = 1000, message = "Description must be between 5 and 1000 characters")
	@Column(nullable = false, length = 1000)
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	private ComplaintStatus status;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@PrePersist
	public void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();

		if (this.status == null) {
			this.status = ComplaintStatus.OPEN;
		}
	}

	@PreUpdate
	public void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}

package com.smartcomplaint.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartcomplaint.dto.ApiResponse;
import com.smartcomplaint.dto.ComplaintCreateRequest;
import com.smartcomplaint.dto.ComplaintResponse;
import com.smartcomplaint.service.ComplaintService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ComplaintController {

	private final ComplaintService complaintService;

	@PostMapping
	public ResponseEntity<ApiResponse<ComplaintResponse>> addComplaint(
			@Valid @RequestBody ComplaintCreateRequest request, Principal principal) {

		String userEmail = principal.getName();

		ComplaintResponse complaintResponse = complaintService.addComplaint(userEmail, request);

		ApiResponse<ComplaintResponse> response = new ApiResponse<>(true, "Complaint added successfully",
				complaintResponse);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/my")
	public ResponseEntity<ApiResponse<List<ComplaintResponse>>> getMyComplaints(Principal principal) {

		String userEmail = principal.getName();

		List<ComplaintResponse> complaints = complaintService.getMyComplaints(userEmail);

		ApiResponse<List<ComplaintResponse>> response = new ApiResponse<>(true, "My complaints fetched successfully",
				complaints);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
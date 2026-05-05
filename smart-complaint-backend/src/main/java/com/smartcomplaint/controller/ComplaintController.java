package com.smartcomplaint.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smartcomplaint.dto.ApiResponse;
import com.smartcomplaint.dto.ComplaintCreateRequest;
import com.smartcomplaint.dto.ComplaintResponse;
import com.smartcomplaint.service.ComplaintService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/complaints")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ComplaintController {

	private final ComplaintService complaintService;

	
	@PostMapping("/user/{userId}")
	public ResponseEntity<ApiResponse<ComplaintResponse>> addComplaint(@PathVariable long userId, @Valid @RequestBody ComplaintCreateRequest request) {

		ComplaintResponse complaintResponse = complaintService.addComplaint(userId, request);

		ApiResponse<ComplaintResponse> response = new ApiResponse<>(true, "Complaint added successfully", complaintResponse);
		
		return new ResponseEntity<ApiResponse<ComplaintResponse>>(response, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<ApiResponse<List<ComplaintResponse>>> getComplaintByUserId(@PathVariable Long userId) {
		
		List<ComplaintResponse> complaints = complaintService.getComplaintsByUserId(userId);
		
		ApiResponse<List<ComplaintResponse>> response = new ApiResponse<>(
				true, "User complaints fetched successfully",
				complaints);
		
		return new ResponseEntity<ApiResponse<List<ComplaintResponse>>>(response, HttpStatus.OK);
	}
}

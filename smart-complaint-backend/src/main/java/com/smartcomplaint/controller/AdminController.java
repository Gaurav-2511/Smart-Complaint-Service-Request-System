package com.smartcomplaint.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartcomplaint.dto.ApiResponse;
import com.smartcomplaint.dto.ComplaintResponse;
import com.smartcomplaint.dto.DashboardStatsResponse;
import com.smartcomplaint.dto.StatusUpdateRequest;
import com.smartcomplaint.service.ComplaintService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	private final ComplaintService complaintService;

	@GetMapping("/complaints")
	public ResponseEntity<ApiResponse<List<ComplaintResponse>>> getAllComplaints(Principal principal) {

	    String adminEmail = principal.getName();

	    List<ComplaintResponse> complaints = complaintService.getAllComplaints();

	    ApiResponse<List<ComplaintResponse>> response = new ApiResponse<>(
	            true,
	            "All complaints fetched successfully by admin: " + adminEmail,
	            complaints
	    );

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/complaints/{id}/status")
	public ResponseEntity<ApiResponse<ComplaintResponse>> updateComplaintStatus(@PathVariable Long id,
			@Valid @RequestBody StatusUpdateRequest request) {
		ComplaintResponse updatedComplaint = complaintService.updateComplaintStatus(id, request);

		ApiResponse<ComplaintResponse> response = new ApiResponse<ComplaintResponse>(true,
				"Complaint status updated successfully", updatedComplaint);

		return new ResponseEntity<ApiResponse<ComplaintResponse>>(response, HttpStatus.OK);
	}

	@DeleteMapping("/complaint/{id}")
	public ResponseEntity<ApiResponse<String>> deleteComplaint(@PathVariable Long id) {
		complaintService.deleteComplaint(id);

		ApiResponse<String> response = new ApiResponse<String>(true, "Complaint deleted successfully",
				"Deleted complaint id: " + id);
		
		return new ResponseEntity<ApiResponse<String>>(response, HttpStatus.OK);
		}

	
	
	
	@GetMapping("/dashboard/stats")
	public ResponseEntity<ApiResponse<DashboardStatsResponse>> getDashboardStats() {
		DashboardStatsResponse stats = complaintService.getDashboardStats();
		
		ApiResponse<DashboardStatsResponse> response = new ApiResponse<DashboardStatsResponse>(
				true, "Dashboard stats fetched successfully", stats);
		
		return new ResponseEntity<ApiResponse<DashboardStatsResponse>>(response, HttpStatus.OK);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

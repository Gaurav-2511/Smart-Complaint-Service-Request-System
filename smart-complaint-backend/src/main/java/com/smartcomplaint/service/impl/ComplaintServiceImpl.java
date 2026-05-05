package com.smartcomplaint.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.smartcomplaint.dto.ComplaintCreateRequest;
import com.smartcomplaint.dto.ComplaintResponse;
import com.smartcomplaint.entity.Complaint;
import com.smartcomplaint.entity.User;
import com.smartcomplaint.enums.ComplaintStatus;
import com.smartcomplaint.repository.ComplaintRepository;
import com.smartcomplaint.repository.UserRepository;
import com.smartcomplaint.service.ComplaintService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

	private final ComplaintRepository complaintRepository;
	private final UserRepository userRepository;
	
	@Override
	public ComplaintResponse addComplaint(Long userId, ComplaintCreateRequest request) {
		
		User user = userRepository.findById(userId)
		.orElseThrow(() -> new RuntimeException("User not found with id: "+userId));
		
		Complaint complaint = new Complaint();
		
		complaint.setTitle(request.getTitle());
		complaint.setDescription(request.getDescription());
		complaint.setStatus(ComplaintStatus.OPEN);
		complaint.setUser(user);
		
		Complaint savedComplaint = complaintRepository.save(complaint);
		
		return mapToResponse(savedComplaint);
	}

	@Override
	public List<ComplaintResponse> getComplaintsByUserId(Long userId) {
		
		boolean userExists = userRepository.existsById(userId);
		
		if(!userExists) {
			throw new RuntimeException("User not found with id: "+userId);
		}
		
		List<Complaint> complaints = complaintRepository.findByUserId(userId);
		
		return complaints.stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	private ComplaintResponse mapToResponse(Complaint complaint) {

		return new ComplaintResponse(complaint.getId(), complaint.getTitle(), complaint.getDescription(),
				complaint.getStatus(), complaint.getCreatedAt(), complaint.getUpdatedAt(), complaint.getUser().getId(),
				complaint.getUser().getName(), complaint.getUser().getEmail());
	}

}

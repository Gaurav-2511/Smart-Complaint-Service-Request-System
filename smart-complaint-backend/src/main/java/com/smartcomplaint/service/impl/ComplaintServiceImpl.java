package com.smartcomplaint.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.smartcomplaint.dto.ComplaintCreateRequest;
import com.smartcomplaint.dto.ComplaintResponse;
import com.smartcomplaint.dto.DashboardStatsResponse;
import com.smartcomplaint.dto.StatusUpdateRequest;
import com.smartcomplaint.entity.Complaint;
import com.smartcomplaint.entity.User;
import com.smartcomplaint.enums.ComplaintStatus;
import com.smartcomplaint.exception.InvalidStatusException;
import com.smartcomplaint.exception.ResourceNotFoundException;
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
    public ComplaintResponse addComplaint(String userEmail, ComplaintCreateRequest request) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + userEmail));

        Complaint complaint = new Complaint();
        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setStatus(ComplaintStatus.OPEN);
        complaint.setUser(user);

        Complaint savedComplaint = complaintRepository.save(complaint);

        return mapToResponse(savedComplaint);
    }

    @Override
    public List<ComplaintResponse> getMyComplaints(String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + userEmail));

        List<Complaint> complaints = complaintRepository.findByUserId(user.getId());

        return complaints.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ComplaintResponse> getAllComplaints() {

        List<Complaint> complaints = complaintRepository.findAll();

        return complaints.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ComplaintResponse updateComplaintStatus(Long complaintId, StatusUpdateRequest request) {

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found with id: " + complaintId));

        ComplaintStatus status;

        try {
            status = ComplaintStatus.valueOf(request.getStatus().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new InvalidStatusException("Invalid status. Allowed values are OPEN, IN_PROGRESS, RESOLVED");
        }

        complaint.setStatus(status);

        Complaint updatedComplaint = complaintRepository.save(complaint);

        return mapToResponse(updatedComplaint);
    }

    @Override
    public void deleteComplaint(Long complaintId) {

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found with id: " + complaintId));

        complaintRepository.delete(complaint);
    }

    @Override
    public DashboardStatsResponse getDashboardStats() {

        long totalComplaints = complaintRepository.count();
        long openComplaints = complaintRepository.countByStatus(ComplaintStatus.OPEN);
        long inProgressComplaints = complaintRepository.countByStatus(ComplaintStatus.IN_PROGRESS);
        long resolvedComplaints = complaintRepository.countByStatus(ComplaintStatus.RESOLVED);

        return new DashboardStatsResponse(
                totalComplaints,
                openComplaints,
                inProgressComplaints,
                resolvedComplaints
        );
    }

    private ComplaintResponse mapToResponse(Complaint complaint) {

        return new ComplaintResponse(
                complaint.getId(),
                complaint.getTitle(),
                complaint.getDescription(),
                complaint.getStatus(),
                complaint.getCreatedAt(),
                complaint.getUpdatedAt(),
                complaint.getUser().getId(),
                complaint.getUser().getName(),
                complaint.getUser().getEmail()
        );
    }
}
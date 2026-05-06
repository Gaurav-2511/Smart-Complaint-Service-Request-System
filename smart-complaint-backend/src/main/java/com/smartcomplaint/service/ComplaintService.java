package com.smartcomplaint.service;

import java.util.List;

import com.smartcomplaint.dto.ComplaintCreateRequest;
import com.smartcomplaint.dto.ComplaintResponse;
import com.smartcomplaint.dto.DashboardStatsResponse;
import com.smartcomplaint.dto.StatusUpdateRequest;

public interface ComplaintService {

    ComplaintResponse addComplaint(String userEmail, ComplaintCreateRequest request);

    List<ComplaintResponse> getMyComplaints(String userEmail);

    List<ComplaintResponse> getAllComplaints();

    ComplaintResponse updateComplaintStatus(Long complaintId, StatusUpdateRequest request);

    void deleteComplaint(Long complaintId);

    DashboardStatsResponse getDashboardStats();
}
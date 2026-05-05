package com.smartcomplaint.service;

import java.util.List;

import com.smartcomplaint.dto.ComplaintCreateRequest;
import com.smartcomplaint.dto.ComplaintResponse;

public interface ComplaintService {

    ComplaintResponse addComplaint(Long userId, ComplaintCreateRequest request);

    List<ComplaintResponse> getComplaintsByUserId(Long userId);
}
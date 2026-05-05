package com.smartcomplaint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartcomplaint.entity.Complaint;
import com.smartcomplaint.enums.ComplaintStatus;


public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

	List<Complaint> findByUserId(Long userId);
	
	long countByStatus(ComplaintStatus status);
}

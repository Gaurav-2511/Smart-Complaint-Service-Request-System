package com.smartcomplaint.dto;

import java.time.LocalDateTime;

import com.smartcomplaint.enums.ComplaintStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ComplaintResponse {

    private Long id;
    private String title;
    private String description;
    private ComplaintStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Long userId;
    private String userName;
    private String userEmail;
}
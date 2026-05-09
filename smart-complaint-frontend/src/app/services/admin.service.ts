import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { ApiResponse } from '../models/api-response.model';
import { DashboardStatsResponse, StatusUpdateRequest } from '../models/admin.model';
import { HttpClient } from '@angular/common/http';
import { ComplaintResponse } from '../models/complaint.model';

@Injectable({
  providedIn: 'root',
})
export class AdminService {

  // Base URL for Admin APIs
  private apiUrl = `${environment.apiBaseUrl}/admin`;

  constructor(private httpClient: HttpClient) { }

  // =========================================
  // GET ADMIN DASHBOARD STATISTICS
  // =========================================
  // Fetches:
  // - Total complaints
  // - Open complaints
  // - In Progress complaints
  // - Resolved complaints
  getDashboardStats(): Observable<ApiResponse<DashboardStatsResponse>> {
    return this.httpClient.get<ApiResponse<DashboardStatsResponse>>(
      `${this.apiUrl}/dashboard/stats`
    );
  }

  // =========================================
  // GET ALL COMPLAINTS
  // =========================================
  // Admin can view all complaints submitted by users
  getAllComplaints(): Observable<ApiResponse<ComplaintResponse[]>> {
    return this.httpClient.get<ApiResponse<ComplaintResponse[]>>(
      `${this.apiUrl}/complaints`
    );
  }

  // =========================================
  // UPDATE COMPLAINT STATUS
  // =========================================
  // Admin can update complaint status:
  // OPEN
  // IN_PROGRESS
  // RESOLVED
  updateComplaintStatus(
    complaintId: number,
    request: StatusUpdateRequest
  ): Observable<ApiResponse<ComplaintResponse>> {

    return this.httpClient.put<ApiResponse<ComplaintResponse>>(
      `${this.apiUrl}/complaints/${complaintId}/status`,
      request
    );
  }

  // =========================================
  // DELETE COMPLAINT
  // =========================================
  // Admin can permanently delete complaint
  deleteComplaint(
    complaintId: number
  ): Observable<ApiResponse<string>> {

    return this.httpClient.delete<ApiResponse<string>>(
      `${this.apiUrl}/complaint/${complaintId}`
    );
  }
}
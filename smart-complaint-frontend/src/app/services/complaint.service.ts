import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { ComplaintCreateRequest, ComplaintResponse } from '../models/complaint.model';
import { Observable } from 'rxjs';
import { ApiResponse } from '../models/api-response.model';

@Injectable({
  providedIn: 'root',
})
export class ComplaintService {

  private apiUrl = environment.apiBaseUrl + '/complaints';

  constructor(private httpClient: HttpClient) { }

  addComplaint(request: ComplaintCreateRequest): Observable<ApiResponse<ComplaintResponse>> {
    return this.httpClient.post<ApiResponse<ComplaintResponse>>(this.apiUrl, request);
  }

  getMyComplaints(): Observable<ApiResponse<ComplaintResponse[]>> {
    return this.httpClient.get<ApiResponse<ComplaintResponse[]>>(`${this.apiUrl}/my`);
  }

}
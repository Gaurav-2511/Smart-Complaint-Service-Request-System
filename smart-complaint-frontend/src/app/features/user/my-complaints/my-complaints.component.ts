import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ComplaintResponse } from '../../../models/complaint.model';
import { ComplaintService } from '../../../services/complaint.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-my-complaints',
  imports: [CommonModule, RouterLink],
  templateUrl: './my-complaints.component.html',
  styleUrl: './my-complaints.component.css',
})
export class MyComplaints implements OnInit{

  complaints: ComplaintResponse[] = [];
  errorMessage = '';
  isLoading = false;

  constructor(private complaintService: ComplaintService, private cdr:ChangeDetectorRef) {}

  ngOnInit(): void {
    this.loadMyComplaints();
  }

  loadMyComplaints(): void {
    this.isLoading = true;
    this.errorMessage = '';

    this.complaintService.getMyComplaints().subscribe({
      next: (response) => {
        this.isLoading = false;
        this.complaints = response.data || [];
        this.cdr.detectChanges();
      },
      error: (error) => {
        this.isLoading = false;
        this.errorMessage = error.error?.message || 'Failed to load complaints';
      }
    });
  }

  getStatusBadgeClass(status: string): string {
    switch (status) {
      case 'OPEN':
        return 'badge bg-warning text-dark';
      case 'IN_PROGRESS':
        return 'badge bg-info text-dark';
      case 'RESOLVED':
        return 'badge bg-success';
      default:
        return 'badge bg-secondary';
    }
  }

}
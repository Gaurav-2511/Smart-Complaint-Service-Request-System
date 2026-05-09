import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ComplaintResponse } from '../../../models/complaint.model';
import { AdminService } from '../../../services/admin.service';

@Component({
  selector: 'app-admin-complaints',
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-complaints.component.html',
  styleUrl: './admin-complaints.component.css',
})
export class AdminComplaints implements OnInit {

  complaints: ComplaintResponse[] = [];
  loading = false;
  errorMessage = '';
  successMessage = '';

  statuses = ['OPEN', 'IN_PROGRESS', 'RESOLVED'] as const;

  constructor(private adminService: AdminService, private cdr:ChangeDetectorRef) {}

  ngOnInit(): void {
    this.loadComplaints();
  }

  loadComplaints(): void {
    this.loading = true;

    this.adminService.getAllComplaints().subscribe({
      next: (response) => {
        this.complaints = response.data;
        this.loading = false;
        this.cdr.detectChanges();
      },
      error: () => {
        this.errorMessage = 'Failed to load complaints';
        this.loading = false;
      }
    });
  }

  updateStatus(complaint: ComplaintResponse, newStatus: 'OPEN' | 'IN_PROGRESS' | 'RESOLVED'): void {
    this.clearMessages();

    this.adminService.updateComplaintStatus(complaint.id, { status: newStatus }).subscribe({
      next: (response) => {
        complaint.status = response.data.status;
        complaint.updatedAt = response.data.updatedAt;
        this.successMessage = 'Complaint status updated successfully';
        this.cdr.detectChanges();
      },
      error: () => {
        this.errorMessage = 'Failed to update complaint status';
      }
    });
  }

  deleteComplaint(id: number): void {
    const confirmDelete = confirm('Are you sure you want to delete this complaint?');

    if (!confirmDelete) {
      return;
    }

    this.clearMessages();

    this.adminService.deleteComplaint(id).subscribe({
      next: () => {
        this.complaints = this.complaints.filter(complaint => complaint.id !== id);
        this.successMessage = 'Complaint deleted successfully';
        this.cdr.detectChanges();
      },
      error: () => {
        this.errorMessage = 'Failed to delete complaint';
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

  clearMessages(): void {
    this.errorMessage = '';
    this.successMessage = '';
    this.cdr.detectChanges();
  }
}
import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ComplaintResponse } from '../../../models/complaint.model';
import { AdminService } from '../../../services/admin.service';

@Component({
  selector: 'app-admin-complaints',
  standalone: true,
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

  updatingComplaintId: number | null = null;
  deletingComplaintId: number | null = null;

  constructor(
    private adminService: AdminService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadAllComplaints();
  }

  loadAllComplaints(): void {
    this.loading = true;
    this.errorMessage = '';
    this.successMessage = '';

    this.adminService.getAllComplaints().subscribe({
      next: (response) => {
        this.complaints = response.data || [];
        this.loading = false;
        this.cdr.detectChanges();
      },
      error: () => {
        this.loading = false;
        this.errorMessage = 'Failed to load complaints';
        this.cdr.detectChanges();
      }
    });
  }

  updateStatus(
    complaint: ComplaintResponse,
    newStatus: 'OPEN' | 'IN_PROGRESS' | 'RESOLVED'
  ): void {
    if (complaint.status === newStatus) {
      return;
    }

    this.clearMessages();
    this.updatingComplaintId = complaint.id;

    this.adminService.updateComplaintStatus(complaint.id, { status: newStatus }).subscribe({
      next: (response) => {
        complaint.status = response.data.status;
        complaint.updatedAt = response.data.updatedAt;
        this.updatingComplaintId = null;
        this.successMessage = 'Complaint status updated successfully';
        this.cdr.detectChanges();
      },
      error: () => {
        this.updatingComplaintId = null;
        this.errorMessage = 'Failed to update complaint status';
        this.cdr.detectChanges();
      }
    });
  }

  deleteComplaint(id: number): void {
    const confirmDelete = confirm('Are you sure you want to delete this complaint?');

    if (!confirmDelete) {
      return;
    }

    this.clearMessages();
    this.deletingComplaintId = id;

    this.adminService.deleteComplaint(id).subscribe({
      next: () => {
        this.complaints = this.complaints.filter(complaint => complaint.id !== id);
        this.deletingComplaintId = null;
        this.successMessage = 'Complaint deleted successfully';
        this.cdr.detectChanges();
      },
      error: () => {
        this.deletingComplaintId = null;
        this.errorMessage = 'Failed to delete complaint';
        this.cdr.detectChanges();
      }
    });
  }

  getStatusBadgeClass(status: string): string {
    switch (status) {
      case 'OPEN':
        return 'badge bg-warning text-dark status-badge';
      case 'IN_PROGRESS':
        return 'badge bg-info text-dark status-badge';
      case 'RESOLVED':
        return 'badge bg-success status-badge';
      default:
        return 'badge bg-secondary status-badge';
    }
  }

  clearMessages(): void {
    this.errorMessage = '';
    this.successMessage = '';
    this.cdr.detectChanges();
  }
}
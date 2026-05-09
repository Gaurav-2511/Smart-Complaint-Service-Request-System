import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ComplaintService } from '../../../services/complaint.service';
import { Router, RouterLink } from '@angular/router';
import { ComplaintCreateRequest } from '../../../models/complaint.model';

@Component({
  selector: 'app-add-complaint',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './add-complaint.component.html',
  styleUrl: './add-complaint.component.css',
})
export class AddComplaint {

  complaintRequest = {
    title: '',
    description: ''
  };

  loading = false;
  successMessage = '';
  errorMessage = '';

  constructor(
    private complaintService: ComplaintService,
    private router: Router
  ) { }

  addComplaint(): void {
    this.successMessage = '';
    this.errorMessage = '';

    if (!this.complaintRequest.title.trim() || !this.complaintRequest.description.trim()) {
      this.errorMessage = 'Title and description are required.';
      return;
    }

    this.loading = true;

    this.complaintService.addComplaint(this.complaintRequest).subscribe({
      next: () => {
        this.loading = false;
        this.successMessage = 'Complaint submitted successfully.';
        this.complaintRequest = {
          title: '',
          description: ''
        };

        setTimeout(() => {
          this.router.navigate(['/user/my-complaints']);
        }, 1000);
      },
      error: () => {
        this.loading = false;
        this.errorMessage = 'Failed to submit complaint. Please try again.';
      }
    });
  }
}
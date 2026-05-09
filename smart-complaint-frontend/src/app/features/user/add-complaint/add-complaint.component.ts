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

  complaint: ComplaintCreateRequest = {
    title: '',
    description: ''
  };

  successMessage = '';
  errorMessage = '';
  isLoading = false;

  constructor(
    private complaintService: ComplaintService,
    private router: Router
  ) { }

  submitComplaint(): void {
    this.successMessage = '';
    this.errorMessage = '';

    if (!this.complaint.title.trim() || !this.complaint.description.trim()) {
      this.errorMessage = 'Title and description are required';
      return;
    }

    this.isLoading = true;

    this.complaintService.addComplaint(this.complaint).subscribe({
      next: (response) => {
        this.isLoading = false;
        this.successMessage = response.message || 'Complaint added successfully';

        this.complaint = {
          title: '',
          description: ''
        };

        setTimeout(() => {
          this.router.navigate(['/user/my-complaints']);
        }, 1000);
      },
      error: (error) => {
        this.isLoading = false;
        this.errorMessage = error.error?.message || 'Something went wrong while adding complaint';
      }
    });
  }
}
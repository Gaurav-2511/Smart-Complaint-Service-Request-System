import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin-complaints',
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-complaints.component.html',
  styleUrl: './admin-complaints.component.css',
})
export class AdminComplaints {

  complaints: any[] = [];

  statusList: string[] = ['OPEN', 'IN_PROGRESS', 'RESOLVED'];

  updateStatus(complaintId: number, status: string): void {
    console.log('Update status');
    console.log('Complaint Id:', complaintId);
    console.log('Status:', status);

    alert('Update status API integration will be implemented in Phase 10');
  }


    deleteComplaint(complaintId: number): void {
    console.log('Delete complaint');
    console.log('Complaint Id:', complaintId);

    alert('Delete complaint API integration will be implemented in Phase 10');
  }
}
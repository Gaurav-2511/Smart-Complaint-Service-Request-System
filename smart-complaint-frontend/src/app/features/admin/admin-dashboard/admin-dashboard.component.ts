import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  imports: [CommonModule, RouterLink],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css',
})
export class AdminDashboard {

  totalComplaints: number = 0;
  openComplaints: number = 0;
  inProgressComplaints: number = 0;
  resolvedComplaints: number = 0;
}
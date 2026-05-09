import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { DashboardStatsResponse } from '../../../models/admin.model';
import { AdminService } from '../../../services/admin.service';

@Component({
  selector: 'app-admin-dashboard',
  imports: [CommonModule, RouterLink],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css',
})
export class AdminDashboard {

  // totalComplaints: number = 0;
  // openComplaints: number = 0;
  // inProgressComplaints: number = 0;
  // resolvedComplaints: number = 0;


  stats: DashboardStatsResponse | null = null;
  loading = false;
  errorMessage = '';

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.loadDashboardStats();
  }

  loadDashboardStats(): void {
    this.loading = true;

    this.adminService.getDashboardStats().subscribe({
      next: (response) => {
        this.stats = response.data;
        this.loading = false;
      },
      error: () => {
        this.errorMessage = 'Failed to load dashboard stats';
        this.loading = false;
      }
    });
  }

}
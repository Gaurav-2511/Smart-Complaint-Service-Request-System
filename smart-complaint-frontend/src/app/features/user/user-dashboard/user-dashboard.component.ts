import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../../services/auth.service.service';

@Component({
  selector: 'app-user-dashboard',
  imports: [CommonModule, RouterLink],
  templateUrl: './user-dashboard.component.html',
  styleUrl: './user-dashboard.component.css',
})
export class UserDashboard {
  userName: string;
  userEmail: string;

  constructor(private authService: AuthService) {
    this.userName = this.authService.getName() ?? '';
    this.userEmail = this.authService.getEmail() ?? '';
  }
}
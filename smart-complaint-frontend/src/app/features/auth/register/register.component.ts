import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { RegisterRequest } from '../../../models/auth.model';
import { AuthService } from '../../../services/auth.service.service';

@Component({
  selector: 'app-register',
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class Register {

  registerRequest: RegisterRequest = {
    name: '',
    email: '',
    password: ''
  };

  successMessage = '';
  errorMessage = '';
  isLoading = false;

  constructor(private authService: AuthService, private router: Router) { }

  onRegister(): void {

    this.successMessage = '';
    this.errorMessage = '';

    if (!this.registerRequest.name || !this.registerRequest.email || !this.registerRequest.password) {
      this.errorMessage = 'All fields are required.';
      return;
    }

    this.isLoading = true;

    this.authService.register(this.registerRequest).subscribe({
      next: (response) => {
        this.isLoading = false;
        this.successMessage = response.message || 'Registration successful. Please login.';

        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 1000);
      },
      error: (error) => {
        this.isLoading = false;
        this.errorMessage = error.error?.message || 'Registration failed. Please try again.';
      }
    });
  }
}
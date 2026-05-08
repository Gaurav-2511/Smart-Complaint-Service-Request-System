import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { LoginRequest } from '../../../models/auth.model';
import { AuthService } from '../../../services/auth.service.service';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class Login {

  loginRequest: LoginRequest = {
    email: '',
    password: ''
  }

  successMessage = '';
  errorMessage = '';
  isLoading = false;

  constructor(private authService: AuthService) { }

  onLogin(): void {

    this.successMessage = '';
    this.errorMessage = '';

    if (!this.loginRequest.email || !this.loginRequest.password) {
      this.errorMessage = 'Email and password are required.';
      return;
    }

    this.isLoading = true;

    this.authService.login(this.loginRequest).subscribe({
      next: (response:any) => {
        console.log(response)
        this.isLoading = false;

        this.authService.saveLoginData(response.data);

        this.successMessage = 'Login successful. Redirecting...';

        setTimeout(() => {
          this.authService.redirectBasedOnRole();
        }, 500);
      },
      error: (error) => {
        this.isLoading = false;
        this.errorMessage = error.error?.message || 'Invalid email or password.';
      }
    });
  }
}
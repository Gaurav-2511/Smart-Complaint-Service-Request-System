import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { LoginRequest, LoginResponse, RegisterRequest } from '../models/auth.model';
import { Observable } from 'rxjs';
import { ApiResponse } from '../models/api-response.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  // Backend API Base URL
  private apiUrl = `${environment.apiBaseUrl}/users`;
  private readonly validRoles = ['USER', 'ADMIN'];

  constructor(
    private httpClient: HttpClient,
    private router: Router
  ) { }

  // =========================
  // REGISTER USER API
  // =========================
  // Sends register data to backend
  register(registerRequest: RegisterRequest): Observable<ApiResponse<string>> {
    return this.httpClient.post<ApiResponse<string>>(
      `${this.apiUrl}/register`,
      registerRequest
    );
  }

  // =========================
  // LOGIN USER API
  // =========================
  // Sends login credentials to backend
  login(loginRequest: LoginRequest): Observable<LoginResponse> {
    return this.httpClient.post<LoginResponse>(
      `${this.apiUrl}/login`,
      loginRequest
    );
  }

  // =========================
  // SAVE LOGIN DATA
  // =========================
  // Stores login response data in localStorage
  saveLoginData(response: LoginResponse): void {
    localStorage.setItem('token', response.token);
    localStorage.setItem('userId', response.userId.toString());
    localStorage.setItem('name', response.name);
    localStorage.setItem('email', response.email);
    localStorage.setItem('role', response.role);
  }

  // =========================
  // GET TOKEN
  // =========================
  // Returns JWT token from localStorage
  getToken(): string | null {
    return localStorage.getItem('token');
  }

  // =========================
  // GET USER ID
  // =========================
  // Returns logged-in user ID
  getUserId(): number | null {
    const userId = localStorage.getItem('userId');
    return userId ? Number(userId) : null;
  }

  // =========================
  // GET USER NAME
  // =========================
  // Returns logged-in user name
  getName(): string | null {
    return localStorage.getItem('name');
  }

  // =========================
  // GET USER EMAIL
  // =========================
  // Returns logged-in user email
  getEmail(): string | null {
    return localStorage.getItem('email');
  }

  // =========================
  // GET USER ROLE
  // =========================
  // Returns logged-in user role
  getRole(): string | null {
    return localStorage.getItem('role');
  }

  // =========================
  // CHECK USER LOGIN STATUS
  // =========================
  // Returns true if token exists and the saved role is valid
  isLoggedIn(): boolean {
    return !!this.getToken() && this.validRoles.includes(this.getRole() ?? '');
  }

  // =========================
  // CHECK USER ROLE
  // =========================
  // Returns true if logged-in user is USER
  isUser(): boolean {
    return this.getRole() === 'USER';
  }

  // =========================
  // CHECK ADMIN ROLE
  // =========================
  // Returns true if logged-in user is ADMIN
  isAdmin(): boolean {
    return this.getRole() === 'ADMIN';
  }

  // =========================
  // GET DASHBOARD URL
  // =========================
  // Returns dashboard route based on role
  getDashboardUrl(): string {
    const role = this.getRole();

    if (role === 'ADMIN') {
      return '/admin/dashboard';
    }

    if (role === 'USER') {
      return '/user/dashboard';
    }

    return '/login';
  }

  // =========================
  // REDIRECT USER
  // =========================
  // Redirects user based on role
  redirectBasedOnRole(): void {
    this.router.navigate([this.getDashboardUrl()]);
  }

  // =========================
  // LOGOUT USER
  // =========================
  // Clears localStorage and redirects to login
  logout(): void {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
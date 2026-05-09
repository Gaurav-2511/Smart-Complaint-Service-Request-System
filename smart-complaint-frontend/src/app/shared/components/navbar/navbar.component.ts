import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../services/auth.service.service';

@Component({
  selector: 'app-navbar',
  imports: [CommonModule, RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
})
export class Navbar {

  constructor(public authService: AuthService, private router:Router) {}



  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  isLoggedIn():boolean{
    return this.authService.isLoggedIn();
  }

  getRole():string | null {
    return this.authService.getRole();
  }

  getName():string | null {
    return this.authService.getName();
  }
}
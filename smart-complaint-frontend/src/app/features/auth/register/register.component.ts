import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class Register {

  name: string = '';
  email: string = '';
  password: string = '';

  onRegister(): void {
    console.log('Register form submitted');
    console.log('Name:', this.name);
    console.log('Email:', this.email);
    console.log('Password:', this.password);

    alert('Register API integration will be implemented in Phase 8');
  }

}
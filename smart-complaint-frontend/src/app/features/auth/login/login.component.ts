import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class Login {

  email:string='';
  password:string='';


  onLogin():void{
    console.log('Login form submitted');
    console.log("Email: ", this.email);
    console.log("Password: ",this.password);

    alert('Login API integration will be implemented in Phase 8');
  }

}
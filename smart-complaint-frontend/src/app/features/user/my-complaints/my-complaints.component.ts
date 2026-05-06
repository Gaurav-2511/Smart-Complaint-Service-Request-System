import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-my-complaints',
  imports: [CommonModule],
  templateUrl: './my-complaints.component.html',
  styleUrl: './my-complaints.component.css',
})
export class MyComplaints {

  complaints:any[] = [];

}
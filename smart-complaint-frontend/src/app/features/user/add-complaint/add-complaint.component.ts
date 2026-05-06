import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-complaint',
  imports: [CommonModule, FormsModule],
  templateUrl: './add-complaint.component.html',
  styleUrl: './add-complaint.component.css',
})
export class AddComplaint {

  title:string = '';
  description:string = '';

    onSubmit(): void {
    console.log('Complaint form submitted');
    console.log('Title:', this.title);
    console.log('Description:', this.description);

    alert('Add complaint API integration will be implemented in Phase 9');
  }

}
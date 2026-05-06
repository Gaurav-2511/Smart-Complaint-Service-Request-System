import { AdminComplaints } from './features/admin/admin-complaints/admin-complaints.component';
import { Routes } from '@angular/router';
import { Login } from './features/auth/login/login.component';
import { Register } from './features/auth/register/register.component';
import { UserDashboard } from './features/user/user-dashboard/user-dashboard.component';
import { AddComplaint } from './features/user/add-complaint/add-complaint.component';
import { MyComplaints } from './features/user/my-complaints/my-complaints.component';
import { AdminDashboard } from './features/admin/admin-dashboard/admin-dashboard.component';

export const routes: Routes = [

  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  { path: 'user/dashboard', component: UserDashboard },
  { path: 'user/add-complaint', component: AddComplaint },
  { path: 'user/my-complaints', component: MyComplaints },
  { path: 'admin/dashboard', component: AdminDashboard },
  { path: 'admin/complaints', component: AdminComplaints },
  { path: '**', redirectTo: 'login' }
];

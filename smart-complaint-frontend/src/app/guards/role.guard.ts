import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service.service';

export const roleGuard: CanActivateFn = (route, state) => {

  const authService = inject(AuthService);
  const router = inject(Router);

  const expectedRole = route.data['role'];
  const currentRole = authService.getRole();

  if (!authService.isLoggedIn()) {
    return router.createUrlTree(['/login']);
  }

  if(currentRole === expectedRole){
    return true;
  }

  return router.createUrlTree([authService.getDashboardUrl()]);
};

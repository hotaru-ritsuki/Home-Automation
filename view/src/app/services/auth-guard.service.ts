import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable, of } from 'rxjs';
import { LocalStorageService } from './local-storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  private isLoggedIn = false;

  constructor(private localStorageService: LocalStorageService, private router: Router) {
    this.localStorageService
      .userIdBehaviourSubject
      .subscribe(userId => this.isLoggedIn = userId !== null);
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot)
    : Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (!this.isLoggedIn) {
      this.localStorageService.clear();
      return this.router.navigateByUrl('/users/login').then(r => r);
    }
    return of<boolean>(true);
  }
}

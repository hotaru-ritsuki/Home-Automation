import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable, of } from 'rxjs';
import { LocalStorageService } from './local-storage.service';

@Injectable({
  providedIn: 'root'
})
export class SignedGuardService implements CanActivate {

  private isLoggedIn;

  constructor(private localStorageService: LocalStorageService, private router: Router) {
    this.localStorageService
      .userIdBehaviourSubject
      .subscribe(userId => this.isLoggedIn = userId !== null && !isNaN(userId));
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot)
    : Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    console.log(this.isLoggedIn);
    if(this.isLoggedIn){
      this.router.navigate(['/dashboard']);
    }
    return of<boolean>(!this.isLoggedIn);
  }
}

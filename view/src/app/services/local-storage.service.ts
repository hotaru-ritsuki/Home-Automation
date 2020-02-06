import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {
  private readonly ACCESS_TOKEN = 'accessToken';
  private readonly REFRESH_TOKEN = 'refreshToken';
  private readonly USER_ID = 'userId';
  private readonly FIRST_NAME = 'firstName';

   firstNameBehaviourSubject: BehaviorSubject<string> = new BehaviorSubject<string>(this.getFirstName());
   userIdBehaviourSubject: BehaviorSubject<number> = new BehaviorSubject<number>(this.getUserId());

  constructor() {
  }

  public getAccessToken(): string {
    return localStorage.getItem(this.ACCESS_TOKEN);
  }

  public setAccessToken(accessToken: string): void {
    localStorage.setItem(this.ACCESS_TOKEN, accessToken);
  }


  public getRefreshToken(): string {
    return localStorage.getItem(this.REFRESH_TOKEN);
  }

  public setRefreshToken(refreshToken: string): void {
    localStorage.setItem(this.REFRESH_TOKEN, refreshToken);
  }

  public getUserId(): number {
    return Number.parseInt(localStorage.getItem(this.USER_ID), 10);
  }

  public setUserId(userId: number): void {
    localStorage.setItem(this.USER_ID, String(userId));
    this.userIdBehaviourSubject.next(this.getUserId());
  }
  public getFirstName(): string {
    return localStorage.getItem(this.FIRST_NAME);
  }

  public setFirstName(firstName: string): void {
    localStorage.setItem(this.FIRST_NAME, firstName);
    this.firstNameBehaviourSubject.next(firstName);
  }

  public clear(): void {
    localStorage.clear();
    this.firstNameBehaviourSubject.next(null);
    this.userIdBehaviourSubject.next(null);
  }

}

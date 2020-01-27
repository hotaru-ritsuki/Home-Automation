import {Injectable} from '@angular/core';
import {LocalStorageService} from './local-storage.service';


@Injectable({
  providedIn: 'root'
})
export class JwtService {

  constructor(private localStorageService: LocalStorageService) {
  }

  public isExpired(token: string): boolean {
    if (token != null) {
      const jwtData = token.split('.')[1];
      const decodedJwtJsonData = window.atob(jwtData);
      const decodedJwtData = JSON.parse(decodedJwtJsonData);
      const dateInSecond = (new Date().getTime() / 1000);
      return dateInSecond > decodedJwtData.exp;
    } else {
      return false;
    }
  }

  getEmailFromAccessToken(): string {
    const accessToken = this.localStorageService.getAccessToken();
    console.log(accessToken);
    if (accessToken == null) {
      return null;
    }
    const payload = accessToken.split('.')[1];
    console.log(payload);
    const decodedPayload = window.atob(payload);
    console.log(decodedPayload);
    console.log(JSON.parse(decodedPayload).sub);
    return JSON.parse(decodedPayload).sub;
  }
}

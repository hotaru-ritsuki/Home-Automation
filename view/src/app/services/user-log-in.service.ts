import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UserLogIn} from '../models/UserLogIn';
import {SuccessLogIn} from '../models/SuccessLogin';
import {LocalStorageService} from '../services/local-storage.service';

@Injectable({
  providedIn: 'root'
})
export class UserLogInService {

  constructor(private http: HttpClient, private localStorageService: LocalStorageService) {
  }

  public logIn(model: UserLogIn) {
    const body = {
      email: model.email,
      password: model.password
    };
    return this.http.post<SuccessLogIn>('http://localhost:8080/users/login', body);
  }

  public saveUserToLocalStorage(data: SuccessLogIn) {
    this.localStorageService.setFirstName(data.firstName);
    this.localStorageService.setAccessToken(data.accessToken);
    this.localStorageService.setRefreshToken(data.refreshToken);
    this.localStorageService.setUserId(Number(data.userId));
  }
}

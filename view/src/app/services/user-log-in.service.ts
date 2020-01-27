import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UserLogIn} from '../models/UserLogIn';
import {SuccessLogIn} from '../models/SuccessLogin';
import {LocalStorageService} from '../services/local-storage.service';
import {ConstantsService} from "./constant/constants.service";

@Injectable({
  providedIn: 'root'
})
export class UserLogInService {
  constant:string;

  constructor(private http: HttpClient, private localStorageService: LocalStorageService, private _constant: ConstantsService) {
  this.constant = this._constant.baseApplicationUrl;
  }

  public logIn(model: UserLogIn) {
    const body = {
      email: model.email,
      password: model.password
    };
    return this.http.post<SuccessLogIn>(this.constant + '/users/login', body);
  }

  public saveUserToLocalStorage(data: SuccessLogIn) {
    this.localStorageService.setFirstName(data.firstName);
    this.localStorageService.setAccessToken(data.accessToken);
    this.localStorageService.setRefreshToken(data.refreshToken);
    this.localStorageService.setUserId(Number(data.userId));
  }
}

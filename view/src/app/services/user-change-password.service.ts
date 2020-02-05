import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ConstantsService} from "./constant/constants.service";
import {UserChangePassword} from "../models/UserChangePassword";

@Injectable({
  providedIn: 'root'
})
export class UserChangePasswordService {
  constant:string;

  constructor(private http: HttpClient, private _constant: ConstantsService) {
    this.constant = this._constant.baseApplicationUrl;
  }

  public changePassword(model: UserChangePassword) {
    const body = {
      currentPassword: model.currentPassword,
      password: model.updPassword
    };
    return this.http.post(this.constant + '/users/changePassword', body);
  }

}

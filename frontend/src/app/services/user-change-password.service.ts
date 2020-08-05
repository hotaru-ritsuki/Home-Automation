import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ConstantsService} from "./constant/constants.service";
import {UserChangePassword} from "../models/UserChangePassword";
import {Observable} from "rxjs";
import {Data} from "../models/Data";
import {Device} from "../models/Device";

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



  public restorePassword(password: string, id: number) {
    let params = new HttpParams();
    params = params.append('password', password);
    params = params.append('id', id.toString());
    const body = {
      password: password,
      id: id
    };
    console.log(this.constant + '/users/changePassword', body);
    return this.http.post(this.constant + '/users/restorePassword',  {
      password,
      id,
    });

  }

  public findUserByEmail(email: string){
    return this.http.get(this.constant + '/users/restorePassword/' + email);
  }
}

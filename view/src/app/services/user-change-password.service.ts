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
    console.log(this.constant + '/users/changePasswordTo', {params});
    return this.http.post(this.constant + '/users/restorePasswordTo',  {
      password,
      id,
    });
  }

  public restorePassword1(id: number, password: string) {
    const body = {
      password: password,
      id: id
    };
    console.log(this.constant + '/users/restorePasswordTo', body);
    return this.http.post(this.constant + '/users/restorePasswordTo', body);
  }

  public findUserByEmail(email: string): Observable<any>{
    return this.http.get<any>(this.constant + '/users/restorePassword/' + email);
  }
}

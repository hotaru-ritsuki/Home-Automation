import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ConstantsService} from "./constant/constants.service";
import {UserChangePassword} from "../models/UserChangePassword";
import {UserChangeInfo} from "../models/UserChangeInfo";

@Injectable({
  providedIn: 'root'
})
export class UserChangeInfoService {
  constant:string;

  constructor(private http: HttpClient, private _constant: ConstantsService) {
    this.constant = this._constant.baseApplicationUrl;
  }
public getInfo(){
    return this.http.get(this.constant+'/users/getInfo');
}

  public changeInfo(model: UserChangeInfo) {
    const body = {
      firstName: model.firstName,
      lastName: model.lastName
    };
    return this.http.post(this.constant + '/users/setInfo', body);
  }

}

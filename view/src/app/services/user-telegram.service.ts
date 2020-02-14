import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {ConstantsService} from "./constant/constants.service";
import {UserTelegram} from "../models/UserTelegram";
import {UserTelegramDTO} from "../models/UserTelegramDTO";

@Injectable({
  providedIn: 'root'
})
export class UserTelegramService {
  constant:string;

  constructor(private http: HttpClient, private _constant: ConstantsService) {
    this.constant = this._constant.baseApplicationUrl;
  }

  public sendActivation(model: UserTelegram) {
    const body={
      username:model.username
    };
    return this.http.post<string>(this.constant + '/users/addTelegram', body);
  }

  public getStatus(){
    return this.http.get<UserTelegramDTO>(this.constant+'/users/getTelegramUser');
}

}

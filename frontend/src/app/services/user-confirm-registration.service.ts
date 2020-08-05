import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ConstantsService} from './constant/constants.service';
import {ActivationEmail} from '../models/ActivationEmail';

@Injectable({
  providedIn: 'root'
})
export class UserConfirmRegistrationService {
  constant: string;

  constructor(private http: HttpClient, private _constant: ConstantsService) {
    this.constant = this._constant.baseApplicationUrl;
  }

  public activate(tokenRequest: string) {
    console.log(this.constant + '/users/confirmRegistration/'+tokenRequest);
    return this.http.get(this.constant + '/users/confirmRegistration/'+tokenRequest);
  }

}

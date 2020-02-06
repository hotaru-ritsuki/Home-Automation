import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ConstantsService} from './constant/constants.service';
import {ActivationEmail} from '../models/ActivationEmail';

@Injectable({
  providedIn: 'root'
})
export class UserResendRegistrationTokenService {
  constant: string;

  constructor(private http: HttpClient, private _constant: ConstantsService) {
    this.constant = this._constant.baseApplicationUrl;
  }

  public activate(model: ActivationEmail) {
    const body = {
      email: model.email
    };
    return this.http.post(this.constant + '/users/resendRegistrationToken', body);
  }

}

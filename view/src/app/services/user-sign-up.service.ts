import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UserSignUp} from '../models/UserSignUp';
import {ConstantsService} from "./constant/constants.service";

@Injectable({
  providedIn: 'root'
})
export class UserSignUpService {

  constructor(private http: HttpClient,private constants: ConstantsService) {
  }

  public signUp(userRegister: UserSignUp) {
    const body = {
      email: userRegister.email,
      password: userRegister.password,
      firstName: userRegister.firstName,
      lastName: userRegister.lastName
    };
    return this.http.post(this.constants.baseApplicationUrl+'/users/register', body);
  }
}

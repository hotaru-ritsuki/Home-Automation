import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UserSignUp} from '../models/UserSignUp';

@Injectable({
  providedIn: 'root'
})
export class UserSignUpService {

  constructor(private http: HttpClient) {
  }

  public signUp(userRegister: UserSignUp) {
    if (userRegister.firstName === undefined) {
      console.log('First name is empty');
      return;
    }
    if (userRegister.lastName === undefined) {
      console.log('Last name is empty');
      return;
    }
    if (userRegister.email === undefined) {
      console.log('Email is empty');
      return;
    }
    if (userRegister.password === undefined) {
      console.log('Password is empty');
      return;
    }

    const body = {
      email: userRegister.email,
      password: userRegister.password,
      firstName: userRegister.firstName,
      lastName: userRegister.lastName
    };
    console.log(body);

    return this.http.post('https://application-appl.herokuapp.com/users/register', body);
  }
}

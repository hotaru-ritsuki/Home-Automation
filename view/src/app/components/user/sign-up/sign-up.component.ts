import { Component, OnInit } from '@angular/core';
import { UserSignUp } from '../../../models/UserSignUp';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import {UserSignUpService} from '../../../services/user-sign-up.service';
import {AlertService} from "../../../services/alert.service";
@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  userSignUp: UserSignUp;
  firstNameErrorMessageBackEnd: string;
  lastNameErrorMessageBackEnd: string;
  emailErrorMessageBackEnd: string;
  passwordErrorMessageBackEnd: string;
  loadingAnim = false;

  constructor(
    private userSecurityService: UserSignUpService,
    private alertService: AlertService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.userSignUp = new UserSignUp();
    this.setNullAllMessage();
  }

  private register(userSignUp: UserSignUp) {
    this.loadingAnim = true;
    this.setNullAllMessage();
    this.userSecurityService.signUp(userSignUp).subscribe(
      () => {
        this.alertService.setMessage("Please check your email to complete registration");
        this.router.navigateByUrl('users/login').then(r => r);
        this.loadingAnim = false;
      },
      (errors: HttpErrorResponse) => {
          this.passwordErrorMessageBackEnd = errors.message;
          this.loadingAnim = false;
        });
  }

  private setNullAllMessage() {
    this.firstNameErrorMessageBackEnd = null;
    this.lastNameErrorMessageBackEnd = null;
    this.emailErrorMessageBackEnd = null;
    this.passwordErrorMessageBackEnd = null;
  }
}

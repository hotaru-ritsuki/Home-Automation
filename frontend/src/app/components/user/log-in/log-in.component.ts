import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { UserLogInService } from '../../../services/user-log-in.service';
import { UserLogIn } from '../../../models/UserLogIn';
import { HttpErrorResponse } from '@angular/common/http';
import { SuccessLogIn } from '../../../models/SuccessLogin';
import { Router } from '@angular/router';
import { AlertService } from '../../../services/alert.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {
  userLogIn: UserLogIn;
  loadingAnim: boolean;
  emailErrorMessageBackEnd: string;
  passwordErrorMessageBackEnd: string;
  backEndError: string;
  public alertMessage:string;

  constructor(
    private userLogInService: UserLogInService,
    private alertService: AlertService,
    private router: Router
  ) {}

  ngOnInit() {
    this.userLogIn = new UserLogIn();
    this.loadingAnim = false;
    this.emailErrorMessageBackEnd = null;
    this.passwordErrorMessageBackEnd = null;
    this.backEndError = null;
    this.alertMessage=this.alertService.getMessage();
  }

  private logIn(userLogIn: UserLogIn) {
    this.loadingAnim = true;
    this.userLogInService.logIn(userLogIn).subscribe(
      (data: SuccessLogIn) => {
        this.userLogInService.saveUserToLocalStorage(data);
        this.router.navigateByUrl('/dashboard')
          .then(success => console.log('redirect has succeeded ' + success))
          .catch(fail => console.log('redirect has failed ' + fail));
        this.loadingAnim = false;
      },
      (errors: HttpErrorResponse) => {
        try {
          errors.error.forEach(error => {
            if (error.name === 'email') {
              this.emailErrorMessageBackEnd = error.message;
            } else if (error.name === 'password') {
              this.passwordErrorMessageBackEnd = error.message;
            }
          });
        } catch (e) {
          this.backEndError = errors.error.message;
        }
        this.loadingAnim = false;
      }
    );
  }

  closeAlert(alert: HTMLDivElement) {
    this.alertService.setMessage("");
    alert.style.display = 'none';
  }
}

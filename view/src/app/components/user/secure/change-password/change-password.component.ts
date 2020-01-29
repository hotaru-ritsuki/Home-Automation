import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import {UserChangePassword} from "../../../../models/UserChangePassword";
import {UserChangePasswordService} from "../../../../services/user-change-password.service";
@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  userChangePassword: UserChangePassword;
  passwordErrorMessageBackEnd: string;
  loadingAnim = false;

  constructor(
    private userChangePasswordService: UserChangePasswordService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.userChangePassword = new UserChangePassword();
    this.setNullAllMessage();
  }

  private changePassword(userChangePassword: UserChangePassword) {
    this.setNullAllMessage();
    this.loadingAnim = true;
    this.userChangePasswordService.changePassword(userChangePassword).subscribe(
      () => {
        this.router.navigateByUrl('').then(r => r);
      },
      (errors: HttpErrorResponse) => {
        this.passwordErrorMessageBackEnd = 'Incorrect Data';
        this.loadingAnim = false;
      });

    this.loadingAnim = false;
  }



  private setNullAllMessage() {
    this.passwordErrorMessageBackEnd = null;
  }
}

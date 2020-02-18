import {Component, OnInit} from '@angular/core';
import {UserLogIn} from "../../../../models/UserLogIn";
import {UserLogInService} from "../../../../services/user-log-in.service";
import {Router} from "@angular/router";
import {UserChangePasswordService} from "../../../../services/user-change-password.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-restore-password',
  templateUrl: './restore-password.component.html',
  styleUrls: ['./restore-password.component.css']
})
export class RestorePasswordComponent implements OnInit {
  userLogIn: UserLogIn;
  public email:string;
  text = 'Enter email for restore.';

  constructor(private userChange: UserChangePasswordService,
              private router: Router) {
  }

  ngOnInit() {
    this.userLogIn = new UserLogIn();
  }

  restorePassword(email: string) {
    console.log(email);
    this.userChange.findUserByEmail(email).subscribe((data:any)=>{
      console.log(data);
    });
    this.text = 'We sent message on your email.';
    console.log(this.text);
  }

}

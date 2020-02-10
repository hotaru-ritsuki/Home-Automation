import {Component, OnInit} from '@angular/core';
import {UserLogIn} from "../../../../models/UserLogIn";
import {UserLogInService} from "../../../../services/user-log-in.service";
import {Router} from "@angular/router";
import {UserChangePasswordService} from "../../../../services/user-change-password.service";

@Component({
  selector: 'app-restore-password',
  templateUrl: './restore-password.component.html',
  styleUrls: ['./restore-password.component.css']
})
export class RestorePasswordComponent implements OnInit {
  userLogIn: UserLogIn;
  user: any;
  email:string;
  error: string;

  constructor(private userChange: UserChangePasswordService) {
  }

  ngOnInit() {
    this.userLogIn = new UserLogIn();
  }

  restorePassword(email: string) {
    this.userChange.findUserByEmail(email).subscribe((response) => {
      this.user = response;
    }, error => {
        this.error = error;
        console.log(this.error);
    });
  }

}

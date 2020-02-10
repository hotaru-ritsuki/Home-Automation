import { Component, OnInit } from '@angular/core';
import {RestorePasswordService} from "../../services/restore-password.service";
import {ActivatedRoute, Router} from "@angular/router";
import {UserChangePassword} from "../../models/UserChangePassword";
import {UserChangePasswordService} from "../../services/user-change-password.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-restore-password-part2',
  templateUrl: './restore-password-part2.component.html',
  styleUrls: ['./restore-password-part2.component.css']
})
export class RestorePasswordPart2Component implements OnInit {
  valid = false;
  userChangePassword: UserChangePassword;
  password1: string;
  password2: string;
  passwordErrorMessageBackEnd: string;
  loadingAnim = false;

  constructor(private restore: RestorePasswordService, private route:ActivatedRoute,
              private userChangePasswordService: UserChangePasswordService, private router: Router) { }

  ngOnInit() {
      this.restore.checkValidRestoreToken(this.route.snapshot.params['id'], this.route.snapshot.params['token'])
        .subscribe((response) => {
          this.valid = false;
        if(response != null){
          this.valid = true;
        }
      }, error => {
          this.router.navigateByUrl('users/login')
        });
    this.userChangePassword = new UserChangePassword();
    this.setNullAllMessage();
  }

  private changePassword(password:string, id: number) {
    this.setNullAllMessage();
    this.loadingAnim = true;
      this.userChangePasswordService.changePassword(password, this.route.snapshot.params['id']).subscribe(
        () => {
          this.router.navigateByUrl('').then(r => r);
        },
        (errors: HttpErrorResponse) => {
          this.passwordErrorMessageBackEnd = 'Incorrect Data';
          this.loadingAnim = false;
        });

      this.router.navigateByUrl('users/login')
      this.loadingAnim = false;

  }


  private setNullAllMessage() {
    this.passwordErrorMessageBackEnd = null;
  }
}



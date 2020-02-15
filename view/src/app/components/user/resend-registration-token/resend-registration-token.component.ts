import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UserResendRegistrationTokenService} from '../../../services/user-resend-registration-token.service';
import {HttpErrorResponse} from '@angular/common/http';
import {ActivationEmail} from '../../../models/ActivationEmail';
import {AlertService} from "../../../services/alert.service";

@Component({
  selector: 'app-resend-registration-token',
  templateUrl: './resend-registration-token.component.html',
  styleUrls: ['./resend-registration-token.component.css']
})
export class ResendRegistrationTokenComponent implements OnInit {
  emailErrorMessageBackEnd: string;
  loadingAnim: boolean;
  activationEmail: ActivationEmail;

  constructor(
    private router: ActivatedRoute,
    private route: Router,
    private userResendRegistrationTokenService: UserResendRegistrationTokenService,
    private alertService: AlertService) {
    this.activationEmail = new ActivationEmail();
  }

  ngOnInit() {
    this.emailErrorMessageBackEnd = null;
    this.loadingAnim = false;

  }

  private resend(email: ActivationEmail) {
    this.loadingAnim = true;
    this.userResendRegistrationTokenService.activate(email).subscribe(() => {
        this.alertService.setMessage("Check your email to complete registration\n Don't forget to activate your account again")
        this.route.navigateByUrl("users/login").then(r => r);
      },
      (errors: HttpErrorResponse) => {
        this.emailErrorMessageBackEnd = errors.error.message
      }
    );

  }

}

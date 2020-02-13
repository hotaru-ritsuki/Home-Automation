import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserResendRegistrationTokenService } from '../../../services/user-resend-registration-token.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivationEmail } from '../../../models/ActivationEmail';

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
    private userResendRegistrationTokenService: UserResendRegistrationTokenService
  ) {}

  ngOnInit() {
    this.emailErrorMessageBackEnd = null;
    this.loadingAnim = false;

  }
private resend(email: ActivationEmail) {
  this.loadingAnim = true;
  this.userResendRegistrationTokenService.activate(email).subscribe((errors: HttpErrorResponse) => {
  errors.error.forEach(error => {
      this.emailErrorMessageBackEnd = error.message;
    }
  );
  this.loadingAnim = false;
});
}

}

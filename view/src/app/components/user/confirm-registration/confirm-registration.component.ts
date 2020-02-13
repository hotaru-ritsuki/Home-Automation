import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserConfirmRegistrationService } from '../../../services/user-confirm-registration.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivationEmail } from '../../../models/ActivationEmail';

@Component({
  selector: 'app-confirm-registration',
  templateUrl: './confirm-registration.component.html',
  styleUrls: ['./confirm-registration.component.css']
})
export class ConfirmRegistrationComponent implements OnInit {
  activationEmail: ActivationEmail;
  emailErrorMessageBackEnd: string;
  loadingAnim = false;
  constructor(
    private router: Router,
    private userConfirmRegistrationService: UserConfirmRegistrationService
  ) {}

  ngOnInit() {
    this.loadingAnim = false;
    this.emailErrorMessageBackEnd = null;

  }
private activate(token: string) {
this.loadingAnim = true;
this.userConfirmRegistrationService.activate(token).subscribe((errors: HttpErrorResponse) => {
  errors.error.forEach(error => {
      this.emailErrorMessageBackEnd = error.message;
    }
  );
  this.loadingAnim = false;
});
this.loadingAnim = false;
}
 
}

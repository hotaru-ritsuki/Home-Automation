import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { UserConfirmRegistrationService } from '../../../services/user-confirm-registration.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-confirm-registration',
  templateUrl: './confirm-registration.component.html',
  styleUrls: ['./confirm-registration.component.css']
})
export class ConfirmRegistrationComponent implements OnInit {
  emailErrorMessageBackEnd: string;
  loadingAnim = false;
  @Output() isActivated: EventEmitter<boolean> = new EventEmitter();
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
this.userConfirmRegistrationService.activate(token).subscribe(
  (data: string) => {
    this.router.navigateByUrl('');
    this.loadingAnim = false;
    this.isActivated.emit(true);
  },
  (errors: HttpErrorResponse) => {
  errors.error.forEach(error => {
      this.emailErrorMessageBackEnd = error.message;
    }
  );
  this.loadingAnim = false;
});
this.loadingAnim = false;
}

}

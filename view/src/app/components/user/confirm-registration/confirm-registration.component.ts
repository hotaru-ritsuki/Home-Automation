import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Router} from '@angular/router';
import {UserConfirmRegistrationService} from '../../../services/user-confirm-registration.service';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-confirm-registration',
  templateUrl: './confirm-registration.component.html',
  styleUrls: ['./confirm-registration.component.css']
})
export class ConfirmRegistrationComponent implements OnInit {
  loadingAnim = false;
  @Output() isActivated: EventEmitter<boolean> = new EventEmitter();

  constructor(
    private router: Router,
    private userConfirmRegistrationService: UserConfirmRegistrationService
  ) {
  }

  ngOnInit() {
    this.loadingAnim = false;

  }

  private activate(token: string) {
    this.loadingAnim = true;
    this.userConfirmRegistrationService.activate(token).subscribe(
      (data: string) => {
        this.loadingAnim = false;
        this.isActivated.emit(true);
        this.router.navigateByUrl('users/login');
      }
        );
        this.loadingAnim = false;
  }

}

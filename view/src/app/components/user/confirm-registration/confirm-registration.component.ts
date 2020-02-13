import {Component, OnInit } from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import {UserConfirmRegistrationService} from '../../../services/user-confirm-registration.service';
import {HttpErrorResponse} from '@angular/common/http';
import {AlertService} from "../../../services/alert.service";

@Component({
  selector: 'app-confirm-registration',
  templateUrl: './confirm-registration.component.html',
  styleUrls: ['./confirm-registration.component.css']
})
export class ConfirmRegistrationComponent implements OnInit {
  isActivated : boolean;
  token: string;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private userConfirmRegistrationService: UserConfirmRegistrationService,
    private alertService: AlertService
  ) {
  }

  ngOnInit() {
    this.token=this.route.snapshot.params['token'];
    this.activate();
    this.alertService.setActivated(true);
    this.router.navigateByUrl('users/login');
  }

  private activate() {
    console.log(this.token);
     this.userConfirmRegistrationService.activate(this.token).subscribe(
       () => {
         this.router.navigateByUrl('users/login');
       }
     );
  }

}

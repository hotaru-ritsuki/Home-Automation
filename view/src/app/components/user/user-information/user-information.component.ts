import {Component, ElementRef, Injectable, OnInit, Pipe, PipeTransform, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import Stepper from 'bs-stepper';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/timer';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/take';
import 'rxjs/add/operator/do';
import {UserChangeInfo} from "../../../models/UserChangeInfo";
import {HttpErrorResponse} from "@angular/common/http";
import {UserTelegramService} from "../../../services/user-telegram.service";
import {UserTelegram} from "../../../models/UserTelegram";
import {UserTelegramDTO} from "../../../models/UserTelegramDTO";
import {UserChangeInfoService} from "../../../services/user-change-info.service";
import {LocalStorageService} from "../../../services/local-storage.service";
import {ActivationTelegram} from "../../../models/ActivationTelegram";

@Injectable()
export class TimerService {
  public counter = 300;
  tick = 1000;

  getCounter() {
    return Observable.timer(0, this.tick)
      .take(this.counter)
      .map(() => --this.counter);
  }
}

@Pipe({
  name: 'formatTime'
})
export class FormatTimePipe implements PipeTransform {

  transform(value: number): string {
    //MM:SS format
    const minutes: number = Math.floor(value / 60);
    return ('00' + minutes).slice(-2) + ':' + ('00' + Math.floor(value - minutes * 60)).slice(-2);

    // for HH:MM:SS
    // const hours: number = Math.floor(value / 3600);
    // const minutes: number = Math.floor((value % 3600) / 60);
    // return ('00' + hours).slice(-2) + ':' + ('00' + minutes).slice(-2) + ':' + ('00' + Math.floor(value - minutes * 60)).slice(-2);

  }
}

@Component({
  selector: 'app-user-information',
  templateUrl: './user-information.component.html',
  styleUrls: ['./user-information.component.css'],
  providers: [TimerService]
})
export class UserInformationComponent implements OnInit {
  private stepper: Stepper;
  loadingAnim = false;
  userChangeInfo: UserChangeInfo;
  activationCode: string;
  firstName: string;
  lastName: string;
  username: UserTelegram;
  selectedTabId = 'info';
  countDown;
  counter = 10;
  userTelegram: UserTelegramDTO;
  public alertMessage: string;
  @ViewChild('alert', {static: true}) alert: ElementRef;


  constructor(
    private router: Router,
    private timerService: TimerService,
    private userTelegramService: UserTelegramService,
    private userChangeInfoService: UserChangeInfoService,
    private localStorageService: LocalStorageService
  ) {
    this.username = new UserTelegram();
    this.userChangeInfo = new UserChangeInfo();
    this.userTelegram = new UserTelegramDTO();
    this.alertMessage = "";
  }

  ngOnInit() {
    this.stepper = new Stepper(document.querySelector('#stepper1'), {
      linear: true,
      animation: true
    });
    this.getInfo();
    this.getStatus();
  }

  public openTelegram() {
    window.open("https://t.me/HomemadeAlertBot", "_blank");
    this.stepper.next();
  }

  public sendActivation() {
    console.log(this.username);
    this.userTelegramService.sendActivation(this.username.username).subscribe(
      (data: ActivationTelegram) => {
        console.log(data);
        this.activationCode = data.activationCode;
        this.countDown = this.timerService.getCounter().do(() => --this.counter);
        this.stepper.next();
      },(errors: HttpErrorResponse) => {
        try {
          errors.error.forEach(error => {
            this.alertMessage = error.message;
          });
        } catch (e) {
          this.alertMessage = errors.error.message;
        }
      }
    );

  }

  public refreshActivation() {
    this.userTelegramService.sendActivation(this.username.username).subscribe(
      (data: ActivationTelegram) => {
        this.activationCode = data.activationCode;
        this.timerService.counter = 300;
      },
      (errors: HttpErrorResponse) => {
        try {
          errors.error.forEach(error => {
            this.alertMessage = error.message;
          });
        } catch (e) {
          this.alertMessage = errors.error.message;
        }
      }
    );
  }

  public getStatus() {
    this.userTelegramService.getStatus().subscribe(
      (data: UserTelegramDTO) => {
        if (data.username === "") {
        } else {
          this.userTelegram = data;
          this.stepper.to(4);
        }
      },
      (errors: HttpErrorResponse) => {
        try {
          errors.error.forEach(error => {
            console.log(error.message);
            this.alertMessage = error.message;
          });
        } catch (e) {
          this.alertMessage = errors.error.message;
          console.log(errors.error.message);
        }
      }
    );
  }

  public refresh() {
    this.stepper.to(1);
  }

  public getInfo() {
    this.userChangeInfoService.getInfo().subscribe(
      (data: UserChangeInfo) => {
        this.userChangeInfo.firstName = data.firstName;
        this.userChangeInfo.lastName = data.lastName;
      },
      (errors: HttpErrorResponse) => {
        try {
          errors.error.forEach(error => {
            this.alertMessage = error.message;
          });
        } catch (e) {
          this.alertMessage = errors.error.message;
        }
      }
    )
  }

  private changeInfo(userChangeInfo: UserChangeInfo) {
    this.userChangeInfoService.changeInfo(userChangeInfo).subscribe(
      (data: UserChangeInfo) => {
        this.localStorageService.setFirstName(data.firstName);
        this.userChangeInfo.firstName = data.firstName;
        this.userChangeInfo.lastName = data.lastName;
        this.alertMessage = "Successfully updated";
      },
      (errors: HttpErrorResponse) => {
        try {
          errors.error.forEach(error => {
            this.alertMessage = "There is a problem";
          });
        } catch (e) {
          this.alertMessage = errors.error.message;
        }
      }
    );
  }

  closeAlert(alert: HTMLDivElement) {
    this.alertMessage = "";
  }
}

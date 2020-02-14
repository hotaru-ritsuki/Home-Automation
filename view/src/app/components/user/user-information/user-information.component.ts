import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Stepper from 'bs-stepper';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/timer';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/take';
import 'rxjs/add/operator/do';
import { Pipe, PipeTransform } from '@angular/core';
import { Injectable } from '@angular/core';
import {UserChangeInfo} from "../../../models/UserChangeInfo";
import {SuccessLogIn} from "../../../models/SuccessLogin";
import {HttpErrorResponse} from "@angular/common/http";
import {UserTelegramService} from "../../../services/user-telegram.service";
import {UserTelegram} from "../../../models/UserTelegram";

@Injectable()
export class TimerService {
  counter = 300;
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
  backEndError: string;
  username:UserTelegram;
  selectedTabId = 'info';
  countDown;
  counter = 10;

  constructor(
    private router: Router,
    private timerService: TimerService,
    private userTelegramService: UserTelegramService
  ) {
    this.username=new UserTelegram();
  }

ngOnInit() {
    this.stepper = new Stepper(document.querySelector('#stepper1'), {
      linear: true,
      animation: true
    });
    this.userChangeInfo = new UserChangeInfo();

    //or
    // this.countDown = this.myService.getCounter();
  }
  private changeInfo(userChangeInfo: UserChangeInfo) {

  }
  public openTelegram(){
    window.open("https://t.me/HomemadeAlertBot", "_blank");
    this.stepper.next();
  }

  public sendActivation() {
    this.userTelegramService.sendActivation(this.username).subscribe(
      (data: string) => {
        this.activationCode=data;
        this.countDown = this.timerService.getCounter().do(() => --this.counter);
        this.stepper.next();
      },
      (errors: HttpErrorResponse) => {
        this.backEndError = errors.error.message;
      }
    );

  }
  public refreshActivation(){
     this.userTelegramService.sendActivation(this.username).subscribe(
     (data: string) => {
        this.activationCode=data;
        this.countDown = this.timerService.getCounter().do(() => --this.counter);
      },
     (errors: HttpErrorResponse) => {
          this.backEndError = errors.error.message;
      }
     );
  }
}

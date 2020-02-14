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
import {UserTelegramDTO} from "../../../models/UserTelegramDTO";
import {UserChangeInfoService} from "../../../services/user-change-info.service";

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
  backEndError: string;
  firstName:string;
  lastName:string;
  username:UserTelegram;
  selectedTabId = 'info';
  countDown;
  counter = 10;
  userTelegram:UserTelegramDTO;

  constructor(
    private router: Router,
    private timerService: TimerService,
    private userTelegramService: UserTelegramService,
    private userChangeInfoService: UserChangeInfoService
  ) {
    this.username=new UserTelegram();
    this.userChangeInfo=new UserChangeInfo();
    this.userTelegram=new UserTelegramDTO();
  }

ngOnInit() {
    this.stepper = new Stepper(document.querySelector('#stepper1'), {
      linear: true,
      animation: true
    });
    this.getInfo();
    this.getStatus();
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
       this.timerService.counter=300;
      },
     (errors: HttpErrorResponse) => {
          this.backEndError = errors.error.message;
      }
     );
  }
  public getStatus(){
    this.userTelegramService.getStatus().subscribe(
      (data: UserTelegramDTO) => {
        this.userTelegram=data;
        this.stepper.to(4);
      },
      (errors: HttpErrorResponse) => {
        this.backEndError = errors.error.message;
      }
    );
  }

  public refresh(){
    this.stepper.to(1);
  }

  public getInfo(){
    this.userChangeInfoService.getInfo().subscribe(
      (data:UserChangeInfo)=>{
        this.userChangeInfo.firstName=data.firstName;
        this.userChangeInfo.lastName=data.lastName;
      },
      (errors: HttpErrorResponse) => {
        this.backEndError = errors.error.message;
      }
    )
  }
  private changeInfo(userChangeInfo: UserChangeInfo){
  this.userChangeInfoService.changeInfo(userChangeInfo).subscribe(
    (data: UserChangeInfo) => {
      this.userChangeInfo.firstName=data.firstName;
      this.userChangeInfo.lastName=data.lastName;
    },
    (errors: HttpErrorResponse) => {
      this.backEndError = errors.error.message;
    }
  );
}

}

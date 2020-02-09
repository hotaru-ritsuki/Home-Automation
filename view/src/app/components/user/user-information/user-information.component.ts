import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Stepper from 'bs-stepper';
import { UserSignUp } from 'src/app/models/UserSignUp';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/timer';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/take';
import 'rxjs/add/operator/do';
import { Pipe, PipeTransform } from '@angular/core';
import { Injectable } from '@angular/core';

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
  userSignUp: UserSignUp;
  selectedTabId = 'info';
  countDown;
  counter = 10;

  constructor(
    private router: Router,
    private timerService: TimerService
  ) {
  }

  onSubmit() {
  }

  next() {
    this.stepper.next();
  }

ngOnInit() {
    this.stepper = new Stepper(document.querySelector('#stepper1'), {
      linear: false,
      animation: true
    });
    this.userSignUp = new UserSignUp();
    this.countDown = this.timerService.getCounter().do(() => --this.counter);
    //or
    // this.countDown = this.myService.getCounter();
  }
  private changeInfo(userSignUp: UserSignUp) {

  }
}

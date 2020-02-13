import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  activated:boolean;
  constructor() {
  }
  public isActivated():boolean{
    return this.activated;
  }
  public setActivated(active:boolean){
    this.activated=active;
  }

}

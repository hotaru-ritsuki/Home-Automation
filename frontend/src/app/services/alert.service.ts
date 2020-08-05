import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  message:string;
  constructor() {
  }
  public getMessage():string{
    return this.message;
  }
  public setMessage(active:string){
    this.message=active;
  }

}

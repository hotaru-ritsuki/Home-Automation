import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Device} from '../models/Device';
import {ConstantsService} from "./constant/constants.service";

@Injectable({
  providedIn: 'root'
})
export class LocalDeviceService {
  answer: any;
  constant:string;

  constructor(private http: HttpClient, private _constant: ConstantsService) {
    this.constant = this._constant.baseApplicationUrl;
  }

  getSupportDevices(): Observable<Device[]> {
    return this.http.get<Device[]>(this.constant + '/devices');
  }

  getLocation(): Observable<Location[]> {
    return this.http.get<Location[]>(this.constant + '/locations');
  }

  findAll(): Observable<Device[]> {
    return this.http.get<Device[]>(this.constant + '/location-devices');
  }

  save(location, supportDevice) {
    var uuid = '2323';
    this.answer = {
      uuid: '213',
      locationId: location.id,
      deviceTemplateId: supportDevice.id
    };
    console.log(this.answer);
    this.http.post(this.constant + '/location-devices', this.answer).subscribe();
  }

}

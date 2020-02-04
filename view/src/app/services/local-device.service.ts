import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Device} from '../models/Device';
import {ConstantsService} from './constant/constants.service';
import {LocalDevice} from "../models/LocalDevice";

@Injectable({
  providedIn: 'root'
})
export class LocalDeviceService {
  answer: any;
  upgrade: any;
  constant: string;

  constructor(private http: HttpClient, private _constant: ConstantsService) {
    this.constant = this._constant.baseApplicationUrl;
  }

  getSupportDevices(): Observable<Device[]> {
    return this.http.get<Device[]>(this.constant + '/devices');
  }

  getLocation(): Observable<Location[]> {
    return this.http.get<Location[]>(this.constant + '/locations');
  }

  getLocalDevice(uuid: string): Observable<Device[]> {
    return this.http.get<Device[]>(this.constant + '/location-devices/' + uuid);
  }

  findAll(): Observable<Device[]> {
    return this.http.get<Device[]>(this.constant + '/location-devices');
  }

  findAllByLocation(id:number): Observable<Location[]> {
    return this.http.get<Location[]>(this.constant + '/location-devices/' + 'location/' + id);
  }

  update(): Observable<Device[]> {
    return this.http.put<Device[]>(this.constant + '/location-devices', this.upgrade);
  }

  delete(uuid: string) {
    this.http.get(this.constant + '/location-devices/' + uuid);
    console.log(this.constant + '/location-devices/' + uuid);
  }

  save(location, supportDevice) : Observable<LocalDevice> {
    let uuid = '2323';
    this.answer = {
      uuid: '213',
      locationId: location.id,
      deviceTemplateId: supportDevice.id
    };
    console.log(this.answer);
    return this.http.post<LocalDevice>(this.constant + '/location-devices', this.answer);
  }

}

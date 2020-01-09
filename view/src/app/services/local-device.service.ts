import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Device} from '../models/Device';

@Injectable({
  providedIn: 'root'
})
export class LocalDeviceService {
  answer: any;

  constructor(private http: HttpClient) {
  }

  getSupportDevices(): Observable<Device[]> {
     return this.http.get<Device[]>('http://localhost:8080/supported-device/getAll');
  }
  getLocation(): Observable<Location[]> {
    return this.http.get<Location[]>('http://localhost:8080/location');
  }

  save(location, supportDevice) {
    this.answer = {
      locationId: location.id,
      supportedDeviceId: supportDevice.id
    };
    console.log(this.answer);
    this.http.post('http://localhost:8080/location-devices/', this.answer).subscribe();
  }

}
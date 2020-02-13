import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Device} from '../models/Device';
import {ConstantsService} from './constant/constants.service';
import {Home} from "../models/Home";
import {LocalDevice} from "../models/LocalDevice";
import {Locations} from "../models/Locations";

@Injectable({
  providedIn: 'root'
})
export class LocalDeviceService {
  constant: string;
  URL: string;
  constructor(private http: HttpClient, private _constant: ConstantsService) {
    this.constant = this._constant.baseApplicationUrl;
    this.URL = this._constant.baseDeviceUrl;
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
  findAllByLocation(id: number): Observable<Location[]> {
    return this.http.get<Location[]>(this.constant + '/location-devices/' + 'location/' + id);
  }
  findAllByHome(id: number): Observable<Home[]> {
    return this.http.get<Home[]>(this.constant + '/location-devices/' + 'home/' + id);
  }
  findLocationByHome(id: number): Observable<Location[]> {
    return this.http.get<Location[]>(this.constant + '/locations/home/' + id);
  }
  delete(uuid: string): Observable<void> {
    return this.http.delete<void>(this.constant + '/location-devices/' + uuid);
  }
  getInfoFromDevice(UUID: string): Observable<any> {
    return this.http.get<any>(this.URL + '/device-data/' + UUID);
  }
  saveLocation(locationDTO: any): Observable<Locations> {
    return this.http.post<Locations>(this.constant + '/locations', locationDTO);
  }
}

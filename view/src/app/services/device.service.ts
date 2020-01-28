import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Device} from '../models/Device';
import {Data} from '../models/Data';
import {ConstantsService} from "./constant/constants.service";

@Injectable({
  providedIn: 'root'
})
export class DeviceService {
  constant:string;

  constructor(private http: HttpClient, private _constant: ConstantsService) {
    this.constant = this._constant.baseApplicationUrl;
  }


  getDevices(): Observable<Device[]> {
    return this.http.get<Device[]>(this.constant + '/devices');
  }

  // @ts-ignore
  getDevicesByFilter(commonRequest): Observable<Data> {
    console.log(commonRequest);
    // const params = new HttpParams()
    //   .set('commonRequest', commonRequest);
    // let FormData = new FormData().append();
    // return this.http.get<Data>(this.URL + '/filter', commonRequest);
  }

}

import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Device} from '../models/Device';
import {Data} from '../models/Data';

@Injectable({
  providedIn: 'root'
})
export class DeviceService {
  URL = 'http://localhost:8080/devices';

  constructor(private http: HttpClient) {
  }

  getDevices(): Observable<Device[]> {
    return this.http.get<Device[]>(this.URL);
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

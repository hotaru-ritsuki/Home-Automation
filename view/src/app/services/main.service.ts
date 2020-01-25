import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {DeviceData} from "../models/DeviceData";
import {ConstantsService} from "./constant/constants.service";


@Injectable({
  providedIn: 'root'
})
export class MainService {
  constant:string;

  constructor(private http: HttpClient, private _constant: ConstantsService) {
    this.constant = this._constant.baseApplicationUrl;
  }


  getAllDeviceData(type, from, to): Observable<DeviceData[]> {
    let params = new HttpParams();
    params = params.append('type', type);
    params = params.append('from', from);
    params = params.append('to', to);

    return this.http.get<DeviceData[]>(this.constant + '/device-data/statistics', {params: params});
  }

}

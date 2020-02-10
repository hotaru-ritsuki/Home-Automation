import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ConstantsService} from "./constant/constants.service";
import {Observable} from "rxjs";
import {Locations} from "../home/model/Locations";
import {LocalDevice} from "../models/LocalDevice";

@Injectable({
  providedIn: 'root'
})
export class AddLocalDeviceService {
  URL: string;
  deviceId: 1;
  constructor(private http: HttpClient, private _constant: ConstantsService) {
    this.URL = _constant.baseApplicationUrl;
  }

  getDeviceById(id: number) {
    this.http.get(this.URL + '');
  }

  getLocationsByHome(id: number): Observable<Locations[]> {
    return this.http.get<Locations[]>(this.URL + '/locations/home/'+ id);
  }
  saveDeviceInLocation(localDeviceRequest: any): Observable<LocalDevice> {
    return this.http.post<LocalDevice>(this.URL + '/location-devices', localDeviceRequest);
  }


}

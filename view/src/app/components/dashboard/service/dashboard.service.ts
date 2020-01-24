import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Locations} from '../../../home/model/Locations';
import {LocalDevice} from '../../../models/LocalDevice';
import {Observable} from 'rxjs';
import {Device} from '../../../models/Device';
import {Feature} from '../../../models/Feature';
import {FeatureDTO} from '../../../models/FeatureDTO';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  private URL = 'http://localhost:8080/location-devices/location';

  constructor(private http: HttpClient) { }

  getLocalDevicesByLocation(location: Locations): Observable<LocalDevice[]> {
    return this.http.get<LocalDevice[]>(this.URL + '/' + location.id);
  }

  getDeviceFeatureByDevice(device: Device) {
    return this.http.get<FeatureDTO[]>('http://localhost:8080/deviceFeatures/' + device.id);
  }
}

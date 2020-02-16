import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {LocalDevice} from '../../../models/LocalDevice';
import {Observable} from 'rxjs';
import {Device} from '../../../models/Device';
import {FeatureDTO} from '../../../models/FeatureDTO';
import {DeviceData} from '../../../models/DeviceData';
import {Locations} from "../../../models/Locations";

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  private URL = 'http://localhost:8080/location-devices/location';
  type: string;
  from: string;
  to: string;
  locationId: number;

  constructor(private http: HttpClient) {
  }

  getLocalDevicesByLocation(location: Locations): Observable<LocalDevice[]> {
    return this.http.get<LocalDevice[]>(this.URL + '/' + location.id);
  }

  getDeviceFeatureByDevice(device: Device) {
    return this.http.get<FeatureDTO[]>('http://localhost:8080/deviceFeatures/' + device.id);
  }

  getCurrentServiceIndicators(uuid: string) {
    return this.http.get<DeviceData>('http://localhost:8081/device-data/' + uuid);
  }

  saveCurrentDeviceData(uuid: string, timestamp: string, data) {
    const currentDate = this.dateParser(new Date(timestamp));
    console.log(uuid, currentDate, data);
    return this.http.post('http://localhost:8081/device-data/', {uuId: uuid, timestamp: currentDate, data});
  }

  dateParser(toParse: Date) {
    return toParse.getFullYear() + '-' + (('0' + (toParse.getMonth() + 1).toString()).slice(-2)) + '-' + (('0' +
      toParse.getDate().toString()).slice(-2)) + 'T' +
      (('0' + toParse.getHours().toString()).slice(-2)) + ':' + (('0' + toParse.getMinutes().toString()).slice(-2))
      + ':' + (('0' + toParse.getSeconds().toString()).slice(-2));
  }

  getAllDeviceData(type1, from1, to1, locationId1): Observable<DeviceData[]> {
    return this.http.post<DeviceData[]>('http://localhost:8081' + '/device-data/statistics',
      {type: type1, from: from1, to: to1, locationId: locationId1});
  }
}

import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {LocalDevice} from '../../../models/LocalDevice';
import {Observable} from 'rxjs';
import {Device} from '../../../models/Device';
import {FeatureDTO} from '../../../models/FeatureDTO';
import {DeviceData} from '../../../models/DeviceData';
import {Locations} from "../../../models/Locations";
import {ConstantsService} from "../../../services/constant/constants.service";

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  private baseApplicationURL = this.constants.baseApplicationUrl;
  private baseDeviceURL = this.constants.baseDeviceUrl;
  type: string;
  from: string;
  to: string;
  locationId: number;

  constructor(private http: HttpClient, private  constants: ConstantsService) {
  }

  getLocalDevicesByLocation(location: Locations): Observable<LocalDevice[]> {
    return this.http.get<LocalDevice[]>(this.baseApplicationURL + '/location-devices/location/' + location.id);
  }

  getDeviceFeatureByDevice(device: Device) {
    return this.http.get<FeatureDTO[]>(this.baseApplicationURL + '/deviceFeatures/' + device.id);
  }

  getCurrentServiceIndicators(uuid: string) {
    return this.http.get<DeviceData>(this.baseDeviceURL + '/device-data/' + uuid);
  }

  saveCurrentDeviceData(uuid: string, timestamp: string, data) {
    const currentDate = this.dateParser(new Date(timestamp));
    return this.http.post(this.baseDeviceURL + '/device-data/', {uuId: uuid, timestamp: currentDate, data});
  }

  dateParser(toParse: Date) {
    return toParse.getFullYear() + '-' + (('0' + (toParse.getMonth() + 1).toString()).slice(-2)) + '-' + (('0' +
      toParse.getDate().toString()).slice(-2)) + 'T' +
      (('0' + toParse.getHours().toString()).slice(-2)) + ':' + (('0' + toParse.getMinutes().toString()).slice(-2))
      + ':' + (('0' + toParse.getSeconds().toString()).slice(-2));
  }

  getAllDeviceData(type1, from1, to1, locationId1): Observable<DeviceData[]> {
    return this.http.post<DeviceData[]>(this.baseDeviceURL + '/device-data/statistics',
      {type: type1, from: from1, to: to1, locationId: locationId1});
  }
}

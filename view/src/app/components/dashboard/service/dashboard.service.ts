import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Locations} from '../../../models/Locations';
import {LocalDevice} from '../../../models/LocalDevice';
import {Observable} from 'rxjs';
import {Device} from '../../../models/Device';
import {FeatureDTO} from '../../../models/FeatureDTO';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  private URL = 'https://application-appl.herokuapp.com/location-devices/location';

  constructor(private http: HttpClient) {
  }

  getLocalDevicesByLocation(location: Locations): Observable<LocalDevice[]> {
    return this.http.get<LocalDevice[]>(this.URL + '/' + location.id);
  }

  getDeviceFeatureByDevice(device: Device) {
    return this.http.get<FeatureDTO[]>('https://application-appl.herokuapp.com/deviceFeatures/' + device.id);
  }
}

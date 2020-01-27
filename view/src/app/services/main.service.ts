import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {DeviceData} from '../models/DeviceData';


@Injectable({
  providedIn: 'root'
})
export class MainService {
  private apiUrl = 'http://localhost:8081';

  constructor(private http: HttpClient) {
  }

  getAllDeviceData(type1, from1, to1, locationId1): Observable<DeviceData[]> {
    console.log(type1, from1, to1, locationId1);
    return this.http.post<DeviceData[]>(this.apiUrl + '/device-data/statistics',
      {type: type1, from: from1, to: to1, locationId: locationId1});
  }
}

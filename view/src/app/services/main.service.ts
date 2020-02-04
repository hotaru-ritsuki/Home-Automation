import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Rule} from "../models/Rule";
import {DeviceData} from "../models/DeviceData";


@Injectable({
  providedIn: 'root'
})
export class MainService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }


  getRules(): Observable<Rule[]> {
    return this.http.get<Rule[]>(this.apiUrl + '/rules')
  }

  getAllDeviceData(type1, from1, to1, locationId1): Observable<DeviceData[]> {
    return this.http.post<DeviceData[]>(this.apiUrl + '/device-data/statistics',
      {type: type1, from: from1, to: to1, locationId: locationId1});
  }
}

import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {DeviceData} from "../models/DeviceData";


@Injectable({
  providedIn: 'root'
})
export class MainService {
  private apiUrl = 'http://localhost:8081';

  constructor(private http: HttpClient) {
  }

  getAllDeviceData(type, from, to, locationId): Observable<DeviceData[]> {
    console.log(from);
    return this.http.post<DeviceData[]>(this.apiUrl + '/device-data/statistics',
      {type:type,from:from,to:to,locationId:locationId});
  }

}

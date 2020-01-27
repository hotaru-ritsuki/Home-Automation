import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {DeviceData} from '../models/DeviceData';


@Injectable({
  providedIn: 'root'
})
export class MainService {
  private apiUrl = 'http://localhost:8081';

  constructor(private http: HttpClient) {
  }

  getAllDeviceData(type, from, to): Observable<DeviceData[]> {
    let params = new HttpParams();
    params = params.append('type', type);
    params = params.append('from', from);
    params = params.append('to', to);

    return this.http.get<DeviceData[]>(this.apiUrl + '/device-data/statistics', {params: params});
  }

}

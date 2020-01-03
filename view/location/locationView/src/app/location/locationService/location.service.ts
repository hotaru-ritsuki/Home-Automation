import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Location} from '../location-component/model/Location';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  URL = 'http://localhost:8080/location'

  constructor(private http: HttpClient) { }

  getLocationByAddress(address: string): Observable<Location[]>{
    return this.http.post<Location[]>(this.URL, address);
  }
}

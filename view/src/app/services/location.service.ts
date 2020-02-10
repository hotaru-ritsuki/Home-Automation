import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import {Locations} from "../models/Locations";
import {ConstantsService} from "./constant/constants.service";

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  constant: string;

  constructor(private http: HttpClient, private _constant: ConstantsService) {
    this.constant = this._constant.baseApplicationUrl;
  }

  getLocations(): Observable<Locations[]> {
    return this.http.get<Locations[]>(this.constant + '/locations');
  }

  getLocation(id: number): Observable<Locations> {
    return this.http.get<Locations>(this.constant + '/locations/' + id);
  }

  postLocation(answer: { name: string, homeId: number }): Observable<Locations> {
    return this.http.post<Locations>(this.constant + '/locations', answer);
  }

  deleteLocation(id: number) {
    return this.http.delete(this.constant + '/locations/' + id);
  }

  putLocation(answer: { id: number, name: string, homeId: number }) {
    return this.http.put(this.constant + '/locations', answer);
  }
}

import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Data} from "../models/Data";
import {Device} from "../models/Device";
import {Feature} from "../models/Feature";

@Injectable({
  providedIn: 'root'
})
export class DevicesTeamplateService {
  URL = 'http://localhost:8080/devices';
  URL2 = 'http://localhost:8080/features';

  constructor(private http: HttpClient) {

  }
  getDevicesByFilter(page, filterRequest): Observable<Data<Device>> {
    let params = new HttpParams();
      params = params.append('model', filterRequest.model);
      params = params.append('type', filterRequest.type);
      params = params.append('brand', filterRequest.brand);
      params = params.append('releaseYear', filterRequest.releaseYear);
      params = params.append('featuresId', filterRequest.featuresId);
    return this.http.get<Data<Device>>(this.URL + '/filter/page=' + page, {params});
  }
  getAllBrands(): Observable<Array<string>> {
    return this.http.get<Array<string>>(this.URL + '/brands');
  }
  getAllYears(): Observable<Array<number>> {
    return this.http.get<Array<number>>(this.URL + '/years');
  }
  getAllFeatures(): Observable<Array<Feature>> {
    return this.http.get<Array<Feature>>(this.URL2);
  }
  getAllTypes(): Observable<Array<string>> {
    return this.http.get<Array<string>>(this.URL + '/types');
  }
  getAllModels(): Observable<Array<string>> {
    return this.http.get<Array<string>>(this.URL + '/models');
  }
}

import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Data} from "../models/Data";
import {Device} from "../models/Device";
import {Feature} from "../models/Feature";
import {ConstantsService} from "./constant/constants.service";

@Injectable({
  providedIn: 'root'
})
export class DevicesTeamplateService {
  URL: string;
  URL2: string;

  constructor(private http: HttpClient, private _constant: ConstantsService) {
    this.URL = this._constant.baseApplicationUrl + '/devices';
    this.URL2 = this._constant.baseApplicationUrl + '/features';
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
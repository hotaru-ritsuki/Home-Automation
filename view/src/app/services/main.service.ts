import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subject} from 'rxjs';
import {Rule} from "../models/Rule";
import {DeviceData} from "../models/DeviceData";
import {LocalDevice} from "../models/LocalDevice";
import {FeatureDTO} from "../models/FeatureDTO";


@Injectable({
  providedIn: 'root'
})
export class MainService {
  private apiUrl = 'http://localhost:8080';
  private currentRule = new Subject();

  constructor(private http: HttpClient) {
  }


  getRules(): Observable<Rule[]> {
    return this.http.get<Rule[]>(this.apiUrl + '/rules')
  }

  getDevicesTypes() {
    return this.http.get<string[]>(this.apiUrl + '/devices/types')
  }

  getAllLocalDevice(): Observable<LocalDevice[]> {
    return this.http.get<LocalDevice[]>(this.apiUrl + '/location-devices')
  }

  saveRule(rule: Rule) {
    return this.http.post(this.apiUrl + '/rules', {rule})
  }

  deleteRule(ruleId){
    return this.http.delete(this.apiUrl + '/rules/'+ruleId);
  }

  changeStatus(rule: Rule) {
    return this.http.put(this.apiUrl + '/rules',{rule})
  }

  getSpecification(id): Observable<FeatureDTO[]> {
    return this.http.get<FeatureDTO[]>(this.apiUrl + '/deviceFeatures/' + id)
  }

  getAllDeviceData(type1, from1, to1, locationId1): Observable<DeviceData[]> {
    return this.http.post<DeviceData[]>(this.apiUrl + '/device-data/statistics',
      {type: type1, from: from1, to: to1, locationId: locationId1});
  }
}

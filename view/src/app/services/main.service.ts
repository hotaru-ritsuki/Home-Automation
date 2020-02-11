import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subject} from 'rxjs';
import {Rule} from "../models/Rule";
import {DeviceData} from "../models/DeviceData";
import {LocalDevice} from "../models/LocalDevice";
import {FeatureDTO} from "../models/FeatureDTO";
import {Action} from "../models/Action";


@Injectable({
  providedIn: 'root'
})
export class MainService {
  private apiUrl = 'http://localhost:8080';
  private currentRule = new Subject();

  constructor(private http: HttpClient) {
  }

  getActions(): Observable<Action[]> {
    return this.http.get<Action[]>(this.apiUrl + '/actions')
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
//   [{"field_name": "temperature", "value": "18", "operator": ">=",
//     "device":{"uuid":"1ec3cf2a-2a3b-11ea-asdd-asdx-as1","home_id":"1"}},
// {"field_name": "humidity", "value": "25", "operator": ">",
//   "device":{"uuid":"1ec3cf2a-2a3b-11ea-asdd-asdx-as1","home_id":"1"}}]


  // state: "12"
  // uuid: "1ec3cf2a-2a3b-11ea-asdd-asdx-as2"
  // currentFeature: "Door"
  // currentOperator: "="
  // type: "Door Sensor"
  // device: "device to check door status"

saveRule(fromData,ActionData,Name,Description) {
    let conditions = '';
  for (let i = 0; i < fromData.length; i++) {
    let condition = {
      field_name:fromData[i].currentFeature.toLowerCase(),
      value:fromData[i].state,
      operator:fromData[i].currentOperator,
      device:{
        uuid:fromData[i].uuid,
        home_id:fromData[i].home_id,
      },
    };
    conditions += (condition + ',');
  }

    let obj = {name:Name,description:Description,active:false,conditions:'[' + conditions + ']'};
    return this.http.post(this.apiUrl + '/rules',obj)
  }

  deleteRule(ruleId) {
    return this.http.delete(this.apiUrl + '/rules/' + ruleId);
  }

  changeStatus(rule: Rule) {
    return this.http.put(this.apiUrl + '/rules', rule)
  }

  getSpecification(id): Observable<FeatureDTO[]> {
    return this.http.get<FeatureDTO[]>(this.apiUrl + '/deviceFeatures/' + id)
  }

  getAllDeviceData(type1, from1, to1, locationId1): Observable<DeviceData[]> {
    return this.http.post<DeviceData[]>(this.apiUrl + '/device-data/statistics',
      {type: type1, from: from1, to: to1, locationId: locationId1});
  }
}

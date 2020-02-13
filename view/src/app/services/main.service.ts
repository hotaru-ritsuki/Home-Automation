import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
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

  getDeviceByUuid(uuid): Observable<LocalDevice> {
    return this.http.get<LocalDevice>(this.apiUrl + '/location-devices/' + uuid);
  }

  saveRuleAction(id: number, actionData) {
    let specification = {};
    for (let i = 0; i < Object.keys(actionData).length; i++) {
      if (Object.keys(actionData)[i] != 'type') {
        specification[Object.keys(actionData)[i]] = actionData[Object.keys(actionData)[i]]
      }
    }
    return this.http.post(this.apiUrl + '/actionsRules', {
      ruleId: id,
      actionId: actionData.type.id, actionSpecification: JSON.stringify(specification)
    })
  }

  updateRule(id: number, fromData: any[], ruleName: any, ruleDescription: any) {
    let conditions = [];
    console.log(fromData);
    for (let i = 0; i < fromData.length; i++) {
      let condition = {
        field_name: fromData[i].currentFeature.toLowerCase(),
        value: fromData[i].state,
        operator: fromData[i].currentOperator,
        device: {
          uuid: fromData[i].device === undefined ? fromData[i].uuid : fromData[i].device.uuid,
          home_id: fromData[i].device === undefined ? fromData[i].home_id : fromData[i].device.home_id,
        },
      };
      conditions.push(condition)
    }
    let obj = {
      id: id,
      name: ruleName,
      description: ruleDescription,
      active: false,
      conditions: JSON.stringify(conditions)
    };
    return this.http.put(this.apiUrl + '/rules', obj)
  }

  saveRule(fromData, Name, Description) {
    let conditions = [];
    for (let i = 0; i < fromData.length; i++) {
      let condition = {
        field_name: fromData[i].currentFeature.toLowerCase(),
        value: fromData[i].state,
        operator: fromData[i].currentOperator,
        device: {
          uuid: fromData[i].uuid,
          home_id: fromData[i].home_id,
        },
      };
      conditions.push(condition)
    }

    let obj = {name: Name, description: Description, active: false, conditions: JSON.stringify(conditions)};
    return this.http.post(this.apiUrl + '/rules', obj)
  }

  updateRuleAction(id: number, actionData: any) {
    let specification = {};
    for (let i = 0; i < Object.keys(actionData).length; i++) {
      if (Object.keys(actionData)[i] != 'type') {
        specification[Object.keys(actionData)[i]] = actionData[Object.keys(actionData)[i]]
      }
    }
    return this.http.put(this.apiUrl + '/actionsRules', {
      ruleId: id,
      actionId: actionData.type.id, actionSpecification: JSON.stringify(specification)
    })
  }

  deleteActionRule(any: any, ruleId: any) {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: {
        ruleId: ruleId,
        actionId: any.type.id
      },
    };
    return this.http.delete(this.apiUrl + '/actionsRules',options )
  }
}

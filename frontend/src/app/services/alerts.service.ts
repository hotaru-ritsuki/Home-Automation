import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ConstantsService} from "./constant/constants.service";
import {Observable} from "rxjs";
import {Alert} from "../models/Alert";

@Injectable({
  providedIn: 'root'
})
export class AlertsService {
  URL: string;

  constructor(private http: HttpClient, private constant: ConstantsService) {
    this.URL = constant.baseDeviceUrl;
  }

  getAlertsByHome(homeId: number): Observable<Alert[]> {
    return this.http.get<Alert[]>(this.URL + '/alerts_list/homes/' + homeId);
  }

}

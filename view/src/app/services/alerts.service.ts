import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ConstantsService} from "./constant/constants.service";

@Injectable({
  providedIn: 'root'
})
export class AlertsService {
  URL: string;
  constructor(private http: HttpClient, private constant: ConstantsService) {
    this.URL = constant.baseApplicationUrl;
  }

}

import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {ConstantsService} from "./constant/constants.service";

@Injectable({
  providedIn: 'root'
})
export class RestorePasswordService {
  constant:string;

  constructor(private http: HttpClient, private _constant: ConstantsService) {
    this.constant = this._constant.baseApplicationUrl;
  }

  checkValidRestoreToken(id:number, token:string): Observable<any> {
    return this.http.get<any>(this.constant + '/users/restorePassword/' + id + '/' + token);
  }
}

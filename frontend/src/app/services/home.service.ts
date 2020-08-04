import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subject} from 'rxjs';
import {Home} from '../models/Home';
import {ConstantsService} from "./constant/constants.service";

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  constant:string;
  URL:string;
  URLFind:string;

  constructor(private http: HttpClient, private _constant: ConstantsService) {
    this.constant = this._constant.baseApplicationUrl;
    this.URL = this._constant.baseApplicationUrl + '/homes';
    this.URLFind = this._constant.baseApplicationUrl +'/homes/find';
  }

  id = new Subject();

  getHomes(): Observable<Home[]> {
    return this.http.get<Home[]>(this.URL);
  }

  getHome(id: number): Observable<Home> {
    return this.http.get<Home>(this.URL + '/' + id);
  }

  postHome(answer: { country: string, city: string, addressa: string }): Observable<Home> {
    return this.http.post<Home>(this.URL, answer);
  }

  deleteHome(id: number) {
    return this.http.delete(this.URL + '/' + id);
  }

  putHome(answer: { id: number, country: string, city: string, addressa: string }) {
    return this.http.put(this.URL, answer);
  }

  getHomeByAddress(address: string): Observable<Home> {
    return this.http.get<Home>(this.URLFind + '/' + address);
  }

  getHomesByUser() {
    return this.http.get<Home[]>(this.URL);
  }
}

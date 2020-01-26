import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subject} from 'rxjs';
import {Home} from '../model/Home';
import {ConstantsService} from "../../services/constant/constants.service";

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  id = new Subject();
  constant:string;

  constructor(private http: HttpClient, private _constant: ConstantsService) {
    this.constant = this._constant.baseApplicationUrl;
  }

  getHomes(): Observable<Home[]> {
    return this.http.get<Home[]>(this.constant + '/homes');
  }

  getHome(id: number): Observable<Home> {
    return this.http.get<Home>(this.constant + '/homes/' + id);
  }

  postHome(answer: { country: string, city: string, addressa: string }): Observable<Home> {
    return this.http.post<Home>(this.constant + '/homes', answer);
  }

  deleteHome(id: number) {
    return this.http.delete(this.constant + '/homes/' + id);
  }

  putHome(answer: { id: number, country: string, city: string, addressa: string }) {
    return this.http.put(this.constant + '/homes', answer);
  }
}

import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class MainServiceService {

  constructor(private http: HttpClient) {}

  getAllCategories(): Observable<> {
    return this.http.get<Category[]>(this.api + '/getCategories');
  }

}

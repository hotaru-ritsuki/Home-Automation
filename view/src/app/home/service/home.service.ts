import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Home} from '../model/Home';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  URL = 'http://localhost:8080/homes';

  constructor(private http: HttpClient) {
  }

  getHomes(): Observable<Home[]> {
    return this.http.get<Home[]>(this.URL);
  }

  getHome(id: number): Observable<Home> {
    return this.http.get<Home>(this.URL + '/' + id);
  }

  postHome(answer: { country: string, city: string, addressa: string }) {
    return this.http.post(this.URL, answer);
  }

  deleteHome(id: number) {
    return this.http.delete(this.URL + '/' + id);
  }

  putHome(answer: { id: number, country: string, city: string, addressa: string }) {
    return this.http.put(this.URL, answer);
  }
}

import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Locations} from '../model/Locations';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  URL = 'http://localhost:8080/locations';

  constructor(private http: HttpClient) {
  }

  getLocation(): Observable<Locations[]> {
    return this.http.get<Locations[]>(this.URL);
  }
}

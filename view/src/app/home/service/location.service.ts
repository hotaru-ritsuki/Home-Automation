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

  getLocations(): Observable<Locations[]> {
    return this.http.get<Locations[]>(this.URL);
  }

  getLocation(id: number): Observable<Locations> {
    return this.http.get<Locations>(this.URL + '/' + id);
  }

  postLocation(answer: { name: string, homeId: number }): Observable<Locations> {
    return this.http.post<Locations>(this.URL, answer);
  }

  deleteLocation(id: number) {
    return this.http.delete(this.URL + '/' + id);
  }

  putLocation(answer: { id: number, name: string, homeId: number }) {
    return this.http.put(this.URL, answer);
  }
}

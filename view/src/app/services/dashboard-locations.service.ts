import {Injectable} from '@angular/core';
import {Locations} from '../models/Locations';

@Injectable({
  providedIn: 'root'
})
export class DashboardLocationsService {
  location: Locations;

  constructor() {
  }

  getLocation() {
    return this.location;
  }

  setLocation(location: Locations) {
    this.location = location;
  }
}

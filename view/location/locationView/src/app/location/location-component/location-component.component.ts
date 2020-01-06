import { Component, OnInit } from '@angular/core';
import {LocationService} from '../locationService/location.service';
import {Location} from '../location-component/model/Location';
@Component({
  selector: 'app-location-component',
  templateUrl: './location-component.component.html',
  styleUrls: ['./location-component.component.css']
})
export class LocationComponent {
  locations: Location[];

  constructor(private locationService: LocationService) {
  }
  getByAddress(address: string) {
    this.locationService.getLocationByAddress(address).subscribe((res) => {
      this.locations = res;
    });
  }
}

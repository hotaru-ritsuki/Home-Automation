import { Component, OnInit } from '@angular/core';
import {HomeService} from '../../../home/service/home.service';
import {Home} from '../../../home/model/Home';
import {Locations} from '../../../home/model/Locations';
import {Router} from '@angular/router';
import {DashboardLocationsService} from '../../../services/dashboard-locations.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  locations: Locations[] = [];
  homes: Home[] = [];
  constructor(private homeService: HomeService, private router: Router, private dashboardLocationsService: DashboardLocationsService) {
    this.router.navigate(['dashboard']);
    this.homeService.getHomes().subscribe(res => {
      this.homes = res;
    });
  }

  searchLocationsByHomeAddress(homeAddress: string) {
    for (const home of this.homes) {
      if (home.addressa === homeAddress) {
        this.locations = home.locations;
      }
    }
  }

  ngOnInit(): void {
  }

  setLocation(location: Locations) {
    this.dashboardLocationsService.setLocation(location);
  }
}

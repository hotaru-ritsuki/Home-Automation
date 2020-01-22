import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Locations} from '../../../home/model/Locations';
import {DashboardLocationsService} from '../../../services/dashboard-locations.service';

@Component({
  selector: 'app-dashboard-locations',
  templateUrl: './dashboard-locations.component.html',
  styleUrls: ['./dashboard-locations.component.css']
})
export class DashboardLocationsComponent implements OnInit {
  private location: Locations;

  constructor(private router: Router, private dashboardLocationsService: DashboardLocationsService) {
    this.router.navigate(['locations']);
  }

  ngOnInit() {
    this.location = this.dashboardLocationsService.getLocation();
  }

}

import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Locations} from '../../../home/model/Locations';
import {DashboardLocationsService} from '../../../services/dashboard-locations.service';
import {LocalDevice} from '../../../models/LocalDevice';
import {LocalDeviceService} from '../../../services/local-device.service';
import {Device} from '../../../models/Device';
import {DashboardService} from '../service/dashboard.service';
import {FeatureDTO} from '../../../models/FeatureDTO';

@Component({
  selector: 'app-dashboard-locations',
  templateUrl: './dashboard-locations.component.html',
  styleUrls: ['./dashboard-locations.component.css']
})
export class DashboardLocationsComponent implements OnInit {
  private location: Locations;
  private localDevices: LocalDevice[] = [];
  private features: FeatureDTO[] = [];
  constructor(private router: Router, private dashboardLocationsService: DashboardLocationsService,
              private localDeviceService: LocalDeviceService, private dashboardService: DashboardService) {
    this.router.navigate(['locations']);
    this.location = this.dashboardLocationsService.getLocation();
    this.dashboardService.getLocalDevicesByLocation(this.location).subscribe(res => {
      this.localDevices = Object.assign([], res);
      console.log(this.localDevices);
      for (const dev of this.localDevices) {
        console.log(dev.deviceTemplate.id);
        this.getFeatureByDevice(dev.deviceTemplate);
      }
    });
  }

  getFeatureByDevice(device: Device) {
      this.dashboardService.getDeviceFeatureByDevice(device).subscribe(res => {
        for (const feat of res) {
          this.features.push(feat);
        }
      });
  }

  ngOnInit() {
  }

}

import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Locations} from '../../../models/Locations';
import {DashboardLocationsService} from '../../../services/dashboard-locations.service';
import {LocalDevice} from '../../../models/LocalDevice';
import {LocalDeviceService} from '../../../services/local-device.service';
import {Device} from '../../../models/Device';
import {DashboardService} from '../service/dashboard.service';
import {FeatureDTO} from '../../../models/FeatureDTO';
import {DataService} from '../../../services/data.service';

@Component({
  selector: 'app-dashboard-locations',
  templateUrl: './dashboard-locations.component.html',
  styleUrls: ['./dashboard-locations.component.css']
})
export class DashboardLocationsComponent implements OnInit {
  private location: Locations;
  private localDevices: LocalDevice[] = [];
  private features: FeatureDTO[] = [];
  private featuresGraphics: FeatureDTO[] = [];
  public minDate: Date = new Date('01/01/2019 00:00 AM');
  public maxDate: Date = new Date('01/01/2021 12:00 AM');
  public dateValue: '';

  constructor(private router: Router, private dashboardLocationsService: DashboardLocationsService,
              private localDeviceService: LocalDeviceService, private dashboardService: DashboardService,
              private dataService: DataService) {
    this.router.navigate(['locations']);
    this.features = [];
    this.location = this.dashboardLocationsService.getLocation();
    this.dashboardService.getLocalDevicesByLocation(this.location).subscribe(res => {
      this.localDevices = Object.assign([], res);
      console.log(this.localDevices);
      for (const dev of this.localDevices) {
        this.getFeatureByDevice(dev.deviceTemplate);
      }
      console.log(this.featuresGraphics);
    });
  }

  getFeatureByDevice(device: Device) {
    this.dashboardService.getDeviceFeatureByDevice(device).subscribe(res => {
      for (const feat of res) {
        if (feat.featureDTO.name === 'getLight') {
          this.features.push(feat);
        } else {
          this.featuresGraphics.push(feat);
        }
      }
    });
  }

  filterByDateTime(from, to) {
    const fromDate = new Date(from);
    const toDate = new Date(to);
    this.dataService.DateTime.next(this.dateParser(fromDate) + '&' + this.dateParser(toDate));
  }

  dateParser(toParse: Date) {
    return toParse.getFullYear() + '-' + (('0' + (toParse.getMonth() + 1).toString()).slice(-2)) + '-' + (('0' +
      toParse.getDate().toString()).slice(-2)) + 'T' +
      (('0' + toParse.getHours().toString()).slice(-2)) + ':' + (('0' + toParse.getMinutes().toString()).slice(-2))
      + ':' + (('0' + toParse.getSeconds().toString()).slice(-2)) + '+01:00';
  }

  ngOnInit() {
  }

}

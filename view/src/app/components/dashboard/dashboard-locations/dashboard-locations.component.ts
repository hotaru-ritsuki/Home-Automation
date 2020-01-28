import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Locations} from '../../../home/model/Locations';
import {DashboardLocationsService} from '../../../services/dashboard-locations.service';
import {LocalDevice} from '../../../models/LocalDevice';
import {LocalDeviceService} from '../../../services/local-device.service';
import {Device} from '../../../models/Device';
import {DashboardService} from '../service/dashboard.service';
import {FeatureDTO} from '../../../models/FeatureDTO';
import {DataService} from '../../../services/data.service';
import {DeviceFeature} from '../../../models/DeviceFeature';

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
  private devicesFeatures: DeviceFeature[] = [];
  private devicesFeaturesGraphics: DeviceFeature[] = [];

  constructor(private router: Router, private dashboardLocationsService: DashboardLocationsService,
              private localDeviceService: LocalDeviceService, private dashboardService: DashboardService,
              private dataService: DataService) {
    this.router.navigate(['locations']);
    this.location = this.dashboardLocationsService.getLocation();
    this.dashboardService.getLocalDevicesByLocation(this.location).subscribe(res => {
      this.localDevices = Object.assign([], res);
      for (const dev of this.localDevices) {
        this.getFeatureByDevice(dev);
      }
    });
    console.log(this.devicesFeatures, this.devicesFeaturesGraphics);
  }

  getFeatureByDevice(localDevice: LocalDevice) {
    this.dashboardService.getDeviceFeatureByDevice(localDevice.deviceTemplate).subscribe(res => {
      for (const feat of res) {
        if (feat.featureDTO.name === 'getLight') {
          const dev: DeviceFeature = new DeviceFeature(localDevice.uuid, feat.featureDTO);
          this.devicesFeatures.push(dev);
        } else {
          const dev: DeviceFeature = new DeviceFeature(localDevice.uuid, feat.featureDTO);
          this.devicesFeaturesGraphics.push(dev);
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

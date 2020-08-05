import {Component, Input, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Locations} from '../../../models/Locations';
import {DashboardLocationsService} from '../../../services/dashboard-locations.service';
import {LocalDevice} from '../../../models/LocalDevice';
import {LocalDeviceService} from '../../../services/local-device.service';
import {DashboardService} from '../service/dashboard.service';
import {DataService} from '../../../services/data.service';
import {DeviceFeature} from '../../../models/DeviceFeature';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {GraphicDialogComponent} from '../graphic-dialog/graphic-dialog.component';

@Component({
  selector: 'app-dashboard-locations',
  templateUrl: './dashboard-locations.component.html',
  styleUrls: ['./dashboard-locations.component.css']
})
export class DashboardLocationsComponent implements OnInit {
  @Input() private location: Locations;
  private localDevices: LocalDevice[] = [];
  public minDate: Date = new Date('01/01/2019 00:00 AM');
  public maxDate: Date = new Date('01/01/2021 12:00 AM');
  public dateValueTo: string;
  public dateValueFrom: string;
  private devicesFeatures: DeviceFeature[] = [];
  private devicesFeaturesGraphics: DeviceFeature[] = [];
  graphicDialog: MatDialogRef<GraphicDialogComponent>;

  constructor(private router: Router, private dashboardLocationsService: DashboardLocationsService,
              private localDeviceService: LocalDeviceService, private dashboardService: DashboardService,
              private dataService: DataService, private dialog: MatDialog) {

  }

  ngOnInit() {
    this.dashboardService.getLocalDevicesByLocation(this.location).subscribe(res => {
      this.localDevices = Object.assign([], res);
      for (const dev of this.localDevices) {
        this.getFeatureByDevice(dev);
      }
    });
  }

  getFeatureByDevice(localDevice: LocalDevice) {
    this.dashboardService.getDeviceFeatureByDevice(localDevice.deviceTemplate).subscribe(res => {
      for (const feat of res) {
        if (feat.featureDTO.name === 'Light' || feat.featureDTO.name === 'Door' || feat.featureDTO.name === 'Window') {
          const dev: DeviceFeature = new DeviceFeature(localDevice.uuid, feat.featureDTO, localDevice);
          this.devicesFeatures.push(dev);
        } else {
          const dev: DeviceFeature = new DeviceFeature(localDevice.uuid, feat.featureDTO, localDevice);
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

  openInDialog(type: string, from: Date, to: Date, locationId: number) {
    this.dashboardService.type = type;
    if (from != null && to != null) {
      this.dashboardService.from = this.dateParser(from);
      this.dashboardService.to = this.dateParser(to);
    } else {
      this.dashboardService.from = '2019-09-12T00:00:00+01:00';
      this.dashboardService.to = '2021-09-12T00:00:00+01:00';
    }
    this.dashboardService.locationId = locationId;
    this.graphicDialog = this.dialog.open(GraphicDialogComponent, {
      width: '60%',
      height: '70%'
    });
  }
}

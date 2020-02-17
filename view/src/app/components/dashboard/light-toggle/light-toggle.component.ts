import {Component, Input, OnInit} from '@angular/core';
import {DashboardService} from '../service/dashboard.service';
import {DeviceData} from '../../../models/DeviceData';
import {Locations} from "../../../models/Locations";

@Component({
  selector: 'app-light-toggle',
  templateUrl: './light-toggle.component.html',
  styleUrls: ['./light-toggle.component.css']
})
export class LightToggleComponent implements OnInit {
  @Input() uuid: string;
  @Input() location: Locations;
  @Input() type: string;
  isTurnedOn: string;

  constructor(private dashboardService: DashboardService) {
  }

  ngOnInit() {
    this.dashboardService.getCurrentServiceIndicators(this.uuid).subscribe(res => {
      console.log(this.type);
      const dev: DeviceData = res;
      const ind = new Map(Object.entries(dev.data)).get(this.type);
      this.isTurnedOn = ind.toString();
    });
    console.log(this.isTurnedOn);
  }

  switchLight() {
    switch (this.type) {
      case 'light':
        if (this.isTurnedOn === 'on') {
          this.isTurnedOn = 'off';
          this.dashboardService.saveCurrentDeviceData(this.uuid, new Date().toDateString(), {light: 'off'}).subscribe();
        } else {
          this.isTurnedOn = 'on';
          this.dashboardService.saveCurrentDeviceData(this.uuid, new Date().toDateString(), {light: 'on'}).subscribe();
        }
        break;
      case 'window':
        if (this.isTurnedOn === 'open') {
          this.isTurnedOn = 'close';
          this.dashboardService.saveCurrentDeviceData(this.uuid, new Date().toDateString(), {window: 'close'}).subscribe();
        } else {
          this.isTurnedOn = 'open';
          this.dashboardService.saveCurrentDeviceData(this.uuid, new Date().toDateString(), {window: 'open'}).subscribe();
        }
        break;
      case 'door':
        if (this.isTurnedOn === 'open') {
          this.isTurnedOn = 'close';
          this.dashboardService.saveCurrentDeviceData(this.uuid, new Date().toDateString(), {door: 'open'}).subscribe();
        } else {
          this.isTurnedOn = 'open';
          this.dashboardService.saveCurrentDeviceData(this.uuid, new Date().toDateString(), {door: 'close'}).subscribe();
        }
        break;
    }
  }
}

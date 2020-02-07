import {Component, Input, OnInit} from '@angular/core';
import {Locations} from '../../../home/model/Locations';
import {DashboardService} from '../service/dashboard.service';
import {LocalDevice} from '../../../models/LocalDevice';
import {Device} from '../../../models/Device';
import {DeviceData} from '../../../models/DeviceData';
import {LightResp} from '../../../models/LightResp';

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
      const dev: DeviceData = res;
      this.isTurnedOn = new Map(Object.entries(dev.data)).get(this.type);
    });
  }

  switchLight() {
    if (this.isTurnedOn === 'on') {
      this.isTurnedOn = 'off';
    } else {
      this.isTurnedOn = 'on';
    }
  }

}

import {Component, OnInit} from '@angular/core';
import {DeviceData} from "../../models/DeviceData";
import {MainService} from "../../services/main.service";

@Component({
  selector: 'app-devices-graphics',
  templateUrl: './devices-graphics.component.html',
  styleUrls: ['./devices-graphics.component.css']
})
export class DevicesGraphicsComponent implements OnInit {
  data: DeviceData[];

  constructor(private service: MainService) {
  }

  ngOnInit() {
    this.service.getAllDeviceData('temperature','2010-01-01T12:00:00+01:00','2020-01-01T12:00:00+01:00')
      .subscribe((res) => {
      console.log(res);
    })
  }

}

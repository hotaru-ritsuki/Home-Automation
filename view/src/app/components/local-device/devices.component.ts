import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LocalDeviceService} from '../../services/local-device.service';
import {Device} from '../../models/Device';

@Component({
  selector: 'app-devices',
  templateUrl: './devices.component.html',
  styleUrls: ['./devices.component.css']
})
export class DevicesComponent {
  locationResponse: any;
  supportDeviceResponse: Device[];

  respLocation: any;
  respSupportDevice: any;

  constructor(private http: HttpClient, private deviceService: LocalDeviceService) {
    this.deviceService.getLocation()
      .subscribe((response) => {
        this.locationResponse = response;
      });
    this.deviceService.getSupportDevices()
      .subscribe((response) => {
        this.supportDeviceResponse = response;
      });
    console.log(this.locationResponse);
  }

  save() {
    this.deviceService.save(this.respLocation, this.respSupportDevice);
  }
}

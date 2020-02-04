import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LocalDeviceService} from '../../services/local-device.service';
import {Device} from '../../models/Device';
import {LocationService} from "../../home/service/location.service";

@Component({
  selector: 'app-devices',
  templateUrl: './devices.component.html',
  styleUrls: ['./devices.component.css']
})
export class DevicesComponent implements OnInit{
  locationResponse: any;
  supportDeviceResponse: Device[];
  allDevice: any;

  respLocation: any;
  respSupportDevice: any;
  allLocationsByHome: any;
  locationId : number;

  constructor(private http: HttpClient, private deviceService: LocalDeviceService, private locationService: LocationService ) {
  }

  ngOnInit() {
    this.deviceService.getLocation()
      .subscribe((response) => {
        this.locationResponse = response;
      });
    this.deviceService.getSupportDevices()
      .subscribe((response) => {
        this.supportDeviceResponse = response;
      });
    this.deviceService.findAll()
      .subscribe((response) => {
        this.allDevice = response;
      });
    this.locationService.getLocations()
      .subscribe((response) => {
        this.allLocationsByHome = response;
      });
  }

  findAll() {
    this.deviceService.findAll()
      .subscribe((response) => {
        this.allDevice = response;
      });
  }

  chooseLocation(id:number) {
    this.locationId = id;
    this.deviceService.findAllByLocation(this.locationId).subscribe((response) => {
      this.allDevice = response;
    });
  }

  delete(uuid: string) {
    this.deviceService.delete(uuid);
    console.log(uuid);
  }
}

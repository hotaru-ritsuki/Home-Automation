import {Component, Input, OnInit} from '@angular/core';
import {AddLocalDeviceService} from "../../services/add-local-device.service";
import {DevicesTeamplateService} from "../../services/devices-teamplate.service";

@Component({
  selector: 'app-add-local-device',
  templateUrl: './add-local-device.component.html',
  styleUrls: ['./add-local-device.component.css']
})
export class AddLocalDeviceComponent implements OnInit {
  deviceId;
  brand;
  model;

  @Input('test') test: number;
  @Input('test2') test2: number;
  allLocations;
  homeId: 1;
  locationId: number;
  descriptionText: string;

  localDeviceRequest = {
    "deviceTemplateId": 1,
    "locationId": this.locationId,
    "uuid": "string"
  };

  constructor(private addLocalDeviceService: AddLocalDeviceService, private deviceTemplateService: DevicesTeamplateService) {
    this.deviceId= deviceTemplateService.savedId;
    this.brand = deviceTemplateService.savedBrand;
    this.model = deviceTemplateService.savedModel;
    this.locationId = 0;
    this.descriptionText = '';
    this.addLocalDeviceService.getLocationsByHome(1).subscribe((res) => {
      this.allLocations = res;
    });
  }

  save() {
    this.localDeviceRequest.locationId = this.locationId;
    console.log(this.localDeviceRequest);
    this.addLocalDeviceService.saveDeviceInLocation(this.localDeviceRequest).subscribe();
  }

  ngOnInit() {
  }



}

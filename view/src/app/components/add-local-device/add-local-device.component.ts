import {Component, OnInit} from '@angular/core';
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
  allLocations;
  homeId: 1;
  locationId: number;
  descriptionText: string;

  localDeviceRequest = {
    "deviceTemplateId": this.deviceId,
    "description": this.descriptionText,
    "locationId": this.locationId,
    "uuid": "string"
  };

  constructor(private addLocalDeviceService: AddLocalDeviceService, private deviceTemplateService: DevicesTeamplateService) {
  }

  save() {
    this.localDeviceRequest.locationId = this.locationId;
    this.localDeviceRequest.description = this.descriptionText;
    this.localDeviceRequest.deviceTemplateId = this.deviceId;
    console.log(this.localDeviceRequest);
    this.addLocalDeviceService.saveDeviceInLocation(this.localDeviceRequest).subscribe();
  }

  ngOnInit() {
    this.deviceId = this.deviceTemplateService.savedId;
    this.brand = this.deviceTemplateService.savedBrand;
    this.model = this.deviceTemplateService.savedModel;
    this.locationId = 0;
    this.addLocalDeviceService.getLocationsByHome(1).subscribe((res) => {
      this.allLocations = res;
    });
  }


}

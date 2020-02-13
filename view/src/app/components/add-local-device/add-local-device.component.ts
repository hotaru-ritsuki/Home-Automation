import {Component, OnInit} from '@angular/core';
import {AddLocalDeviceService} from "../../services/add-local-device.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-add-local-device',
  templateUrl: './add-local-device.component.html',
  styleUrls: ['./add-local-device.component.css']
})
export class AddLocalDeviceComponent implements OnInit {
  homeId: number;
  locationId: number;
  locationIdForCheck: number;
  deviceId: number;
  deviceBrand: string;
  deviceModel: string;
  allLocations;
  descriptionText: string;
  homeName: string;

  localDeviceRequest = {
    "deviceTemplateId": this.deviceId,
    "description": this.descriptionText,
    "locationId": this.locationId,
    "uuid": "string"
  };

  constructor(private addLocalDeviceService: AddLocalDeviceService,
              private route: ActivatedRoute,
              private router: Router) {

  }

  ngOnInit() {
    this.homeName = this.route.snapshot.params['home_name'];
    this.descriptionText = '';
    this.homeId = this.route.snapshot.params['home'];
    this.locationId = this.route.snapshot.params['location'];
    console.log(this.locationId);
    this.deviceId = this.route.snapshot.params['device'];
    this.deviceBrand = this.route.snapshot.params['brand'];
    this.deviceModel = this.route.snapshot.params['model'];
    this.addLocalDeviceService.getLocationsByHome(this.homeId).subscribe((res) => {
      this.allLocations = res;
    });
  }

  save() {
    this.localDeviceRequest.locationId = this.locationId;
    this.localDeviceRequest.description = this.descriptionText;
    this.localDeviceRequest.deviceTemplateId = this.deviceId;
    console.log(this.localDeviceRequest);
    this.addLocalDeviceService.saveDeviceInLocation(this.localDeviceRequest).subscribe();


    setTimeout(() => {
      this.router.navigateByUrl('device/' + this.homeName + '/' + this.homeId + '/location/' + this.locationId);
    }, 100)
  }

  back() {
    this.router.navigateByUrl('device-template/' + this.homeName + '/' + this.homeId + '/location/' + this.locationId);
  }


}

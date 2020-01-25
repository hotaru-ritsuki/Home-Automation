import { Component, OnInit } from '@angular/core';
import {Device} from "../../../models/Device";
import {Feature} from "../../../models/Feature";
import {DevicesTeamplateService} from "../../../services/devices-teamplate.service";

@Component({
  selector: 'app-device-template',
  templateUrl: './device-template.component.html',
  styleUrls: ['./device-template.component.css']
})
export class DeviceTemplateComponent implements OnInit {
  pageForUrl: number;
  countOfPages: number;
  devicesWithFilter: {
    content: Device[],
    totalPages: number,
    totalElements: number
  };
  filterRequest = {
    brand: '',
    featuresId: [],
    model: '',
    releaseYear: '',
    type: ''
  };
  featuresId: Array<number> = [];
  allBrands: Array<string>;
  allYears: Array<number>;
  allFeatures: Array<Feature>;
  allTypes: Array<string>;
  allModels: Array<string>;

  constructor(private deviceService: DevicesTeamplateService) {
  }

  ngOnInit() {
    this.pageForUrl = 0;
    this.deviceService.getAllFeatures().subscribe((res) => {
      this.allFeatures = res;
    });
    this.deviceService.getAllBrands().subscribe((res) => {
      this.allBrands = res;
    });
    this.deviceService.getAllYears().subscribe((res) => {
      this.allYears = res;
    });
    this.deviceService.getAllTypes().subscribe((res) => {
      this.allTypes = res;
    });
    this.deviceService.getAllModels().subscribe((res) => {
      this.allModels = res;
    });
    this.getAllDevices();
  }
  getAllDevices() {
    this.checkFeaturesCheckBox();
    this.deviceService.getDevicesByFilter(this.pageForUrl, this.filterRequest).subscribe((res) => {
      this.countOfPages = res.totalPages;
      this.devicesWithFilter = res;
    });
  }

  checkFeaturesCheckBox() {
    const features = [];
    let input;
    const inputElements = document.getElementsByClassName('featuresCheckbox');
    for (let i = 0; inputElements[i]; ++i) {
      input = inputElements[i];
      if (input.checked) {
        features.push(input.value);
      }
    }
    this.filterRequest.featuresId = features;
  }

  selectBrand(event: any) {
    this.filterRequest.brand = event.target.value;
  }

  selectYear(event: any) {
    this.filterRequest.releaseYear = event.target.value;
  }
  selectType(event: any) {
    this.filterRequest.type = event.target.value;
  }
  selectModels(event: any) {
    this.filterRequest.model = event.target.value;
  }

}

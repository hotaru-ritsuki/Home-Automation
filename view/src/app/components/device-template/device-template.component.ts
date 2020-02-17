import {Component, OnInit} from '@angular/core';
import {Device} from "../../models/Device";
import {Feature} from "../../models/Feature";
import {DevicesTeamplateService} from "../../services/devices-teamplate.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Home} from "../../models/Home";

@Component({
  selector: 'app-device-template',
  templateUrl: './device-template.component.html',
  styleUrls: ['./device-template.component.css']
})
export class DeviceTemplateComponent implements OnInit {
  pageForUrl: number;
  totalPages: number;
  countOfPages: Array<number>;
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
    type: '',
    image: ''
  };
  featuresId: Array<number> = [];
  allBrands: Array<string>;
  allYears: Array<number>;
  allFeatures: Array<Feature>;
  allTypes: Array<string>;
  allModels: Array<string>;
  home: Home;
  homeId: number;
  homeName: string;
  locationId: number;


  constructor(private deviceService: DevicesTeamplateService,
              private route: ActivatedRoute,
              private router: Router) {

  }

  ngOnInit() {
    this.homeId = this.route.snapshot.params['home'];
    this.homeName = this.route.snapshot.params['home_name'];
    this.locationId = this.route.snapshot.params['location'];
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
    const pages = [];
    this.deviceService.getDevicesByFilter(this.pageForUrl, this.filterRequest).subscribe((res) => {
      for (let i = 0; i < res.totalPages; i++) {
        pages.push(i);
      }
      this.totalPages = res.totalPages;
      this.countOfPages = pages;
      this.devicesWithFilter = res;
      this.pageForUrl = 0;
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

  getPage(page: number) {
    this.pageForUrl = page;
    this.getAllDevices();
  }

  selectButton(deviceId, deviceBrand, deviceModel) {
    let homeId = this.route.snapshot.params['home'];
    let locationId = this.route.snapshot.params['location'];
    console.log(this.router.navigateByUrl('add-local-device/' + this.homeName + '/' + homeId + '/' + locationId + '/' + deviceId +
      '/' + deviceBrand + '/' + deviceModel));
  }

  clearButton() {
    let input;
    // @ts-ignore
    document.getElementById("device-brand").value = '';
    // @ts-ignore
    document.getElementById("device-model").value = '';
    // @ts-ignore
    document.getElementById("device-type").value = '';
    // @ts-ignore
    document.getElementById("device-year").value = '';
    this.filterRequest.brand = '';
    this.filterRequest.model = '';
    this.filterRequest.releaseYear = '';
    this.filterRequest.type = '';
    const inputElements = document.getElementsByClassName('featuresCheckbox');
    for (let i = 0; inputElements[i]; ++i) {
      input = inputElements[i];
      input.checked = false;
    }
    this.getAllDevices();

  }

}

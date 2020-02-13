import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {LocalDeviceService} from '../../services/local-device.service';
import {Device} from '../../models/Device';
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {ModalComponent} from "../modal/modal.component";
import {ActivatedRoute, Router} from "@angular/router";
import {LocationModalComponent} from "../location-modal/location-modal.component";
import {Home} from "../../models/Home";
import {LocationService} from "../../services/location.service";
import {UpdateLocationComponent} from "../update-location/update-location.component";
import {HomeService} from "../../services/home.service";

@Component({
  selector: 'app-devices',
  templateUrl: './devices.component.html',
  styleUrls: ['./devices.component.css']
})
export class DevicesComponent implements OnInit {
  locationResponse: any;
  supportDeviceResponse: Device[];
  allDevice: any;
  home: Home;
  getInfo = {
    id: '',
    uuId: '',
    timestamp: '',
    data: []
  };

  allLocationsByHome: any;
  locationId: number;
  homeId: number;
  homeName: string;
  matDialog: MatDialogRef<ModalComponent>;
  matDialogLocation: MatDialogRef<LocationModalComponent>;
  locationExist = false;

  constructor(private http: HttpClient, private deviceService: LocalDeviceService, public dialog: MatDialog,
              private route: ActivatedRoute, private router: Router, private locationService: LocationService, private homeService: HomeService) {
    this.homeId = this.route.snapshot.params['home'];
    this.homeName = this.route.snapshot.params['home_name'];
    this.deviceService.getLocation()
      .subscribe((response) => {
        this.locationResponse = response;
      });
    this.deviceService.getSupportDevices()
      .subscribe((response) => {
        this.supportDeviceResponse = response;
      });
    this.findAllDevice(this.route.snapshot.params['home'], this.route.snapshot.params['location']);
    this.deviceService.findLocationByHome(this.homeId)
      .subscribe((response) => {
        this.allLocationsByHome = response;
      });
    this.homeService.getHome(this.homeId).subscribe((res) => {
      this.home = res;
    });
  }

  ngOnInit() {
  }

  chooseHome() {
    this.locationId = 0;

    this.deviceService.findAllByHome(this.homeId).subscribe((response) => {
      this.allDevice = response;
    });
    this.locationExist = false;
    this.router.navigateByUrl('device/' + this.homeName + '/' + this.homeId + '/location/' + 0);
  }

  deleteLocation(id: number, $event: MouseEvent) {
    $event.stopPropagation();
    this.locationService.deleteLocation(id).subscribe();

    this.locationId = 0;

    setTimeout(() =>{
      this.deviceService.findLocationByHome(this.homeId)
        .subscribe((response) => {
          this.allLocationsByHome = response;
        });
      this.router.navigateByUrl('device/' + this.homeName + '/' + this.homeId + '/location/' + 0);
    }, 50);
  }

  findAllDevice(home: number, location: number) {
    if (location != 0) {
      this.chooseLocation(location);
      this.locationExist = true;
    } else {
      this.deviceService.findAllByHome(home)
        .subscribe((response) => {
          this.allDevice = response;
          this.locationId = 0;
          this.locationExist = false;
        });
      this.router.navigateByUrl('device/' + this.homeName + '/' + this.homeId + '/location/' + 0);
    }
  }

  chooseLocation(id: number) {
    this.locationId = id;
    this.deviceService.findAllByLocation(this.locationId).subscribe((response) => {
      this.allDevice = response;
    });
    this.locationExist = true;
    this.router.navigateByUrl('device/' + this.homeName + '/' + this.homeId + '/location/' + id);
  }

  openModal(uuid: string) {
    let dialogRef = this.dialog.open(ModalComponent, {
      data: {
        name: 'Are you sure, you want to delete this device?',
        uuid: uuid
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (this.locationId == 0) {
        this.deviceService.findAllByHome(this.homeId)
          .subscribe((response) => {
            this.allDevice = response;
          });
      } else {
        this.chooseLocation(this.locationId);
      }
    });
  }

  openLocationModal(locationName: string) {
    let dialogRef = this.dialog.open(UpdateLocationComponent, {
      data: {
        name: locationName,
        id: this.locationId,
        homeId: this.homeId
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (this.locationId == 0) {
        this.deviceService.findAllByHome(this.homeId)
          .subscribe((response) => {
            this.allDevice = response;
          });
      } else {
        this.deviceService.findLocationByHome(this.homeId)
          .subscribe((response) => {
            this.allLocationsByHome = response;
          });
        this.chooseLocation(this.locationId);
      }
    });
  }

  openModalLocation() {
    let dialogRef = this.dialog.open(LocationModalComponent, {
      data: {
        name: 'Are you sure, you want to delete this device?',
        homeId: this.homeId
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.locationId = this.route.snapshot.params['location'];
      console.log(this.locationId);
      if (this.locationId == 0) {
        this.deviceService.findAll()
          .subscribe((response) => {
            this.allDevice = response;
          });
      } else {
        this.chooseLocation(this.locationId);
      }


      this.deviceService.findLocationByHome(this.homeId)
        .subscribe((response) => {
          this.allLocationsByHome = response;
        });
    });
  }

  homeIsNull() {
    return this.home === undefined;
  }

  addNewDevice() {
    this.router.navigateByUrl('device-template/' + this.homeName + '/' + this.homeId + '/location/' + this.locationId);
  }

  getInfoFromDevice(UUID: string) {
    this.deviceService.getInfoFromDevice(UUID).subscribe((response) => {
      this.getInfo = response;
    }, (errors: HttpErrorResponse) => {
      this.getInfo = {
        id: '',
        uuId: '',
        timestamp: '',
        data: []
      };
    });
  }
}

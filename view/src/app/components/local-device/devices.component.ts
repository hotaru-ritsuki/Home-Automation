import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LocalDeviceService} from '../../services/local-device.service';
import {Device} from '../../models/Device';
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {ModalComponent} from "../modal/modal.component";
import {ActivatedRoute, Router} from "@angular/router";
import {stringify} from "querystring";
import {daLocale} from "ngx-bootstrap";
import {LocationModalComponent} from "../location-modal/location-modal.component";

@Component({
  selector: 'app-devices',
  templateUrl: './devices.component.html',
  styleUrls: ['./devices.component.css']
})
export class DevicesComponent implements OnInit {
  locationResponse: any;
  supportDeviceResponse: Device[];
  allDevice: any;
  getInfo = {
    id: '',
    uuId: '',
    timestamp: '',
    data: []
  };

  allLocationsByHome: any;
  locationId: number;
  homeId: number;
  matDialog: MatDialogRef<ModalComponent>;
  matDialogLocation: MatDialogRef<LocationModalComponent>;
  locationExist = false;

  constructor(private http: HttpClient, private deviceService: LocalDeviceService, public dialog: MatDialog,
              private route: ActivatedRoute, private router: Router) {

  }

  ngOnInit() {
    this.homeId = this.route.snapshot.params['home'];
    this.deviceService.getLocation()
      .subscribe((response) => {
        this.locationResponse = response;
      });
    this.deviceService.getSupportDevices()
      .subscribe((response) => {
        this.supportDeviceResponse = response;
      });
    this.findAllDevice(this.route.snapshot.params['location']);
    this.deviceService.findLocationByHome(this.homeId)
      .subscribe((response) => {
        this.allLocationsByHome = response;
      });
  }

  chooseHome(id: number) {
    this.homeId = id;
    this.locationId = 0;
    this.deviceService.findAllByHome(this.homeId).subscribe((response) => {
      this.allDevice = response;
    });
    this.locationExist = false;
    this.router.navigateByUrl('device/home/' + this.homeId + '/location/' + 0);
  }

  findAllDevice(location: number) {
    if (location != 0) {
      this.chooseLocation(location);
      this.locationExist = true;
    } else {
      this.deviceService.findAll()
        .subscribe((response) => {
          this.allDevice = response;
          this.locationId = 0;
          this.locationExist = false;
        });
      this.router.navigateByUrl('device/home/' + this.homeId + '/location/' + 0);
    }
  }

  chooseLocation(id: number) {
    this.locationId = id;
    this.deviceService.findAllByLocation(this.locationId).subscribe((response) => {
      this.allDevice = response;
    });
    this.locationExist = true;
    this.router.navigateByUrl('device/home/' + this.homeId + '/location/' + id);
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
        this.deviceService.findAll()
          .subscribe((response) => {
            this.allDevice = response;
          });
      } else {
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

  addNewDevice() {
    this.router.navigateByUrl('device-template/home/' + this.homeId + '/location/' + this.locationId);
  }

  getInfoFromDevice(UUID: string) {
    this.deviceService.getInfoFromDevice(UUID) .subscribe((response) => {
      this.getInfo = response;
    });
  }
}

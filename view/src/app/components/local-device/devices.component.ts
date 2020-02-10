import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LocalDeviceService} from '../../services/local-device.service';
import {Device} from '../../models/Device';
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {ModalComponent} from "../modal/modal.component";

@Component({
  selector: 'app-devices',
  templateUrl: './devices.component.html',
  styleUrls: ['./devices.component.css']
})
export class DevicesComponent implements OnInit {
  locationResponse: any;
  supportDeviceResponse: Device[];
  allDevice: any;

  allLocationsByHome: any;
  locationId: number;
  homeId: number;
  matDialog: MatDialogRef<ModalComponent>;


  constructor(private http: HttpClient, private deviceService: LocalDeviceService, public dialog: MatDialog) {
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
        this.locationId = 0;
      });
    this.deviceService.findLocationByHome(1)
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
  }

  chooseLocation(id: number) {
    this.locationId = id;
    this.deviceService.findAllByLocation(this.locationId).subscribe((response) => {
      this.allDevice = response;
    });
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
}

import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {LocalDeviceService} from "../../services/local-device.service";
import {Router} from "@angular/router";
import {DevicesComponent} from "../local-device/devices.component";

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit {
  uuid: string;
  matDialog: MatDialogRef<DevicesComponent> ;

  constructor(private dialog: MatDialogRef<ModalComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private deviceService: LocalDeviceService, private router: Router) {
    this.uuid = data.uuid;
  }


  ngOnInit() {
  }

  delete() {
    this.deviceService.delete(this.uuid).subscribe(r=>r);
    this.dialog.close();
    console.log(this.uuid);

    this.deviceService.findAll()
      .subscribe((response) => {
      });
  }

  close() {
    this.dialog.close();
    this.dialog.afterClosed().subscribe()
  }
}

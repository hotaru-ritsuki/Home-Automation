import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DevicesComponent} from "../local-device/devices.component";
import {ModalComponent} from "../modal/modal.component";
import {ActivatedRoute, Router} from "@angular/router";
import {LocationService} from "../../services/location.service";

@Component({
  selector: 'app-delete-location',
  templateUrl: './delete-location.component.html',
  styleUrls: ['./delete-location.component.css']
})
export class DeleteLocationComponent implements OnInit {

  homeName: string;
  homeId: number;
  locationId;
  id: number;
  matDialog: MatDialogRef<DevicesComponent>;

  constructor(private dialog: MatDialogRef<ModalComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private locationService: LocationService,
              private route: ActivatedRoute, private router: Router) {
    this.id = data.id;
    this.homeId = data.homeId;
    this.homeName = data.homeName;
    this.locationId = data.locationId;
  }


  ngOnInit() {
  }

  delete() {
    this.locationService.deleteLocation(this.id).subscribe();

    setTimeout(() =>{
      this.dialog.close();
    }, 100);
  }

  close() {
    this.dialog.close();
    this.router.navigateByUrl('device/' + this.homeName + '/' + this.homeId + '/location/' + this.locationId);
    this.dialog.afterClosed().subscribe()
  }
}

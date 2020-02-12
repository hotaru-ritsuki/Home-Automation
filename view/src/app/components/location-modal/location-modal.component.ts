import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {LocalDeviceService} from "../../services/local-device.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-location-modal',
  templateUrl: './location-modal.component.html',
  styleUrls: ['./location-modal.component.css']
})
export class LocationModalComponent implements OnInit {
  newLocationId: number;
  getHomeId: number;
  homeName: string;
  locationName: string;
  locationDTO = {
    homeId: this.getHomeId,
    id: '',
    name: ''
  }

  constructor(private dialog: MatDialogRef<LocationModalComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private deviceService: LocalDeviceService,
              private router: Router,
              private route: ActivatedRoute) {
    this.locationDTO.homeId = this.data.homeId;
  }

  ngOnInit() {
  this.homeName = this.route.snapshot.params['home_name'];
  }

  save() {
    this.locationDTO.name = this.locationName;
    this.deviceService.saveLocation(this.locationDTO).subscribe((response) => {
      this.newLocationId = response.id;
      console.log(response);
      console.log(response.id);
      this.router.navigateByUrl('device/' + this.homeName + '/' + this.locationDTO.homeId + '/location/' + response.id);

    });
    this.dialog.close();
  }

  close() {
    this.dialog.close();
    this.dialog.afterClosed().subscribe()
  }
}

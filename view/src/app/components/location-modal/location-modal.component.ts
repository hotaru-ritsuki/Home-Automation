import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {LocalDeviceService} from "../../services/local-device.service";
import {ActivatedRoute, Router} from "@angular/router";
import {LocationService} from "../../services/location.service";

@Component({
  selector: 'app-location-modal',
  templateUrl: './location-modal.component.html',
  styleUrls: ['./location-modal.component.css']
})
export class LocationModalComponent implements OnInit {
  newLocationId: number;
  getHomeId: number;
  homeName: string;
  locationName: string = null;
  locationDTO = {
    homeId: this.getHomeId,
    id: '',
    name: ''
  };
  alert = 1;

  constructor(private dialog: MatDialogRef<LocationModalComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private deviceService: LocalDeviceService,
              private locationService: LocationService,
              private router: Router,
              private route: ActivatedRoute) {
    this.locationDTO.homeId = this.data.homeId;
  }

  ngOnInit() {
    this.homeName = this.route.snapshot.params['home_name'];
  }

  save() {
    if(this.locationName != null && this.locationName != '') {
      if (this.locationName.length > 0 || this.locationName.length < 10) {
        this.locationDTO.name = this.locationName;

        this.deviceService.saveLocation(this.locationDTO).subscribe((response) => {
          this.newLocationId = response.id;

          this.router.navigateByUrl('device/' + this.homeName + '/' + this.locationDTO.homeId + '/location/' + response.id);
        });
      } else {
        this.alert = 2;
      }
    } else {
      this.alert = 2;
    }
    this.dialog.close();
  }

  close() {
    this.dialog.close();
    this.dialog.afterClosed().subscribe()
  }
}

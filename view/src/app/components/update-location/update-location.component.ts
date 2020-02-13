import {Component, Inject, OnInit} from '@angular/core';
import {LocalDeviceService} from "../../services/local-device.service";
import {LocationService} from "../../services/location.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Locations} from "../../models/Locations";

@Component({
  selector: 'app-update-location',
  templateUrl: './update-location.component.html',
  styleUrls: ['./update-location.component.css']
})
export class UpdateLocationComponent implements OnInit {
  getHomeId: number;
  locationName: string;
  locationDTO = {
    homeId: this.getHomeId,
    id: '',
    name: ''
  };
  id: number;
  home: number;
  locationId: number;
  location: Locations;

  constructor(private dialog: MatDialogRef<UpdateLocationComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private deviceService: LocalDeviceService,
              private locationService: LocationService,
              private router: Router,
              private route: ActivatedRoute) {
    this.locationDTO.homeId = this.data.homeId;
    this.id = data.id;
    this.home = data.homeId;
    this.locationName = data.name;
  }

  ngOnInit() {
    this.locationId = this.route.snapshot.params['id'];
  }

  save(name: string) {
    console.log('ff ' + this.data.id);

    this.locationService.update({id: this.id, name: name, homeId: this.home}).subscribe();

    this.dialog.close();
  }

  close() {
    this.dialog.close();
    this.dialog.afterClosed().subscribe()
  }
}

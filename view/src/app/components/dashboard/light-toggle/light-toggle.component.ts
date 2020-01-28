import {Component, Input, OnInit} from '@angular/core';
import {Locations} from '../../../home/model/Locations';

@Component({
  selector: 'app-light-toggle',
  templateUrl: './light-toggle.component.html',
  styleUrls: ['./light-toggle.component.css']
})
export class LightToggleComponent implements OnInit {
  @Input() deviceId: number;
  @Input() location: Locations;
  isTurnedOn: boolean;

  constructor() {
  }

  ngOnInit() {
    this.isTurnedOn = false;
  }

  switchLight() {
    this.isTurnedOn = !this.isTurnedOn;
  }

}

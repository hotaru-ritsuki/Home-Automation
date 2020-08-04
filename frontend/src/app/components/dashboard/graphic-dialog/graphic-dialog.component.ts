import {Component, OnInit} from '@angular/core';
import {DashboardService} from "../service/dashboard.service";

@Component({
  selector: 'app-graphic-dialog',
  templateUrl: './graphic-dialog.component.html',
  styleUrls: ['./graphic-dialog.component.css']
})
export class GraphicDialogComponent implements OnInit {
  type: string;
  from: Date;
  to: Date;
  locationId: number;
  fromString: string;
  toString: string;

  constructor(private dashboardService: DashboardService) {
  }

  ngOnInit() {
    this.type = this.dashboardService.type;
    this.to = new Date(this.dashboardService.to);
    this.from = new Date(this.dashboardService.from);
    this.locationId = this.dashboardService.locationId;
    this.fromString = this.dateParser(this.from);
    this.toString = this.dateParser(this.to);
  }

  dateParser(toParse: Date) {
    return toParse.getFullYear() + '-' + (('0' + (toParse.getMonth() + 1).toString()).slice(-2)) + '-' + (('0' +
      toParse.getDate().toString()).slice(-2)) + 'T' +
      (('0' + toParse.getHours().toString()).slice(-2)) + ':' + (('0' + toParse.getMinutes().toString()).slice(-2))
      + ':' + (('0' + toParse.getSeconds().toString()).slice(-2)) + '+01:00';
  }

}

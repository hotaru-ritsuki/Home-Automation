import {Component, OnInit} from '@angular/core';
import {DataService} from "../../services/data.service";

@Component({
  selector: 'app-graphics-dashbord',
  templateUrl: './graphics-dashbord.component.html',
  styleUrls: ['./graphics-dashbord.component.css']
})
export class GraphicsDashbordComponent implements OnInit {
  public minDate: Date = new Date("01/01/2019 00:00 AM");
  public maxDate: Date = new Date("01/01/2021 12:00 AM");
  public dateValue: '';

  constructor(private dataService: DataService) {
  }

  locationId = 2;

  ngOnInit() {
  }

  //add if
  filterByDateTime(from, to) {
    let fromDate = new Date(from);
    let toDate = new Date(to);
    this.dataService.DateTime.next(this.dateParser(fromDate) + '&' + this.dateParser(toDate) + '&' +
      this.locationId);
  }

  dateParser(toParse: Date) {
    return toParse.getFullYear() + '-' + ('0' + toParse.getMonth() + 1).slice(-2) + '-' +
      ('0'+toParse.getDate()).slice(-2) + 'T' +
      ('0'+toParse.getHours()).slice(-2)  + ':' +  ('0'+toParse.getMinutes()).slice(-2)
      + ':' +  ('0'+toParse.getSeconds()).slice(-2)  + '+01:00'
  }

}

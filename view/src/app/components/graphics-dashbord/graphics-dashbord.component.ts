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

  ngOnInit() {
  }

  //add if
  filterByDateTime(from, to) {
    let fromDate = new Date(from);
    let toDate = new Date(to);
    this.dataService.DateTime.next(this.dateParser(fromDate) + '&' + this.dateParser(toDate));
  }

  dateParser(toParse: Date) {
    return toParse.getFullYear() + '-' + (toParse.getMonth() + 1) + '-' + toParse.getDate() + 'T' +
      toParse.getHours() + ':' + toParse.getMinutes() + ':' + toParse.getSeconds() + '+01:00'
  }
}

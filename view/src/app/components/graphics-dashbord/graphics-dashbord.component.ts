import {Component, OnInit} from '@angular/core';
import {DataService} from '../../services/data.service';

@Component({
  selector: 'app-graphics-dashbord',
  templateUrl: './graphics-dashbord.component.html',
  styleUrls: ['./graphics-dashbord.component.css']
})
export class GraphicsDashbordComponent implements OnInit {
  public minDate: Date = new Date('01/01/2019 00:00 AM');
  public maxDate: Date = new Date('01/01/2021 12:00 AM');
  public dateValue: '';

  constructor(private dataService: DataService) {
  }

  ngOnInit() {
  }

  filterByDateTime(from, to) {
    console.log(from + 'asfasfafasfasf');
    const fromDate = new Date(from);
    const toDate = new Date(to);
    this.dataService.DateTime.next(this.dateParser(fromDate) + '&' + this.dateParser(toDate));
  }

  dateParser(toParse: Date) {
    console.log(toParse);
    return this.pad(toParse.getFullYear()) + '-' + this.pad((toParse.getMonth() + 1)) + '-' + this.pad(toParse.getDate()) + 'T' +
      this.pad(toParse.getHours()) + ':' + this.pad(toParse.getMinutes()) + ':' + this.pad(toParse.getSeconds()) + '+01:00';
  }

  pad(n) {
    if (n.toString().length < 2) {
      return '0' + n.toString();
    }
  }
}

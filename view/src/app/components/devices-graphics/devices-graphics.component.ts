import {Component, Input, OnInit} from '@angular/core';
import {DeviceData} from '../../models/DeviceData';
import {MainService} from '../../services/main.service';
import {DataService} from '../../services/data.service';
import {DashboardService} from "../dashboard/service/dashboard.service";

@Component({
  selector: 'app-devices-graphics',
  templateUrl: './devices-graphics.component.html',
  styleUrls: ['./devices-graphics.component.css']
})
export class DevicesGraphicsComponent implements OnInit {
  @Input() type: string;
  @Input() from: string;
  @Input() to: string;
  @Input() locationId: number;

  constructor(private service: MainService, private dateService: DataService,private dashService:DashboardService) {
  }

  ChartOptions = {
    scales: {
      yAxes: [{
        ticks: {
          beginAtZero: false
        }
      }],
      xAxes: [{
        ticks: {
          fontSize: 10
        }
      }]
    },
    scaleShowVerticalLines: false,
    responsive: true,
    title: {
      display: false,
      text: 'graphic'
    }
  };
  ChartLabels = [];
  ChartType = 'line';
  ChartLegend = true;
  ChartData = [
    {data: [], label: '', fill: true, backgroundColor: 'rgba(0, 0, 0, 0)'},
  ];


  ngOnInit() {
    if (this.to === '' && this.from === '') {
      this.from = '2019-09-12T00:00:00+01:00';
      this.to = '2021-09-12T00:00:00+01:00';
    }
    this.getData(this.from, this.to);
    this.dateService.DateTime.subscribe((dateTime: string) => {
      const arr = dateTime.split('&');
      this.getData(arr[0], arr[1]);
    });
  }

  getData(dateFrom, dateTo) {
    this.dashService.getAllDeviceData(this.type, dateFrom, dateTo, this.locationId)
      .subscribe((res: DeviceData[]) => {
        const temperatures = [];
        for (const one of res) {
          this.ChartLabels.push(one.timeStamp.replace('T', '\n'));
          const temp = new Map(Object.entries(one.data)).get(this.type);
          temperatures.push(temp);
        }
        this.ChartData = [{data: temperatures, label: this.type, fill: true, backgroundColor: 'rgba(0, 0, 0, 0)'}];
      });
  }

  dateParser(toParse: Date) {
    return toParse.getFullYear() + '-' + (('0' + (toParse.getMonth() + 1).toString()).slice(-2)) + '-' + (('0' +
      toParse.getDate().toString()).slice(-2)) + 'T' +
      (('0' + toParse.getHours().toString()).slice(-2)) + ':' + (('0' + toParse.getMinutes().toString()).slice(-2))
      + ':' + (('0' + toParse.getSeconds().toString()).slice(-2)) + '+01:00';
  }
}

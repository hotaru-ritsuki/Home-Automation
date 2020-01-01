import {Component, Input, OnInit} from '@angular/core';
import {DeviceData} from "../../models/DeviceData";
import {MainService} from "../../services/main.service";
import {DataService} from "../../services/data.service";

@Component({
  selector: 'app-devices-graphics',
  templateUrl: './devices-graphics.component.html',
  styleUrls: ['./devices-graphics.component.css']
})
export class DevicesGraphicsComponent implements OnInit {
  @Input() type: string;
  @Input() from: string;
  @Input() to: string;

  constructor(private service: MainService, private dateService: DataService) {
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
   this.getData("2019-01-01T12:00:00+01:00","2021-01-01T12:00:00+01:00");
    this.dateService.DateTime.subscribe((dateTime:string) => {
      let arr = dateTime.split('&');
      this.getData(arr[0],arr[1]);
    });

  }

  getData(dateFrom,dateTo) {
    this.service.getAllDeviceData(this.type, dateFrom, dateTo)
      .subscribe((res: DeviceData[]) => {
        let temperatures = [];
        for (const one of res) {
          this.ChartLabels.push(one.timeStamp.replace("T", "\n"));
          let temp = new Map(Object.entries(one.data)).get(this.type);
          temperatures.push(temp)
        }
        this.ChartData = [{data: temperatures, label: this.type, fill: true, backgroundColor: 'rgba(0, 0, 0, 0)'}]
      })
  }

}

import {Component, OnInit} from '@angular/core';
import {AlertsService} from "../../services/alerts.service";
import {LocalStorageService} from "../../services/local-storage.service";
import {HomeService} from "../../services/home.service";
import {Alert} from "../../models/Alert";

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css']
})
export class AlertComponent implements OnInit {
  alert = {
    data: '',
    description: '',
    homeId: '',
    id: '',
    timestamp: "2020-02-18T00:32:25.536Z",
    uuId: ''
  };
  allAlerts: Alert[] = [];
  userId;
  allHomes = [];

  constructor(private alertsService: AlertsService,
              private localStorage: LocalStorageService,
              private homeService: HomeService) {
    this.homeService.getHomesByUser().subscribe((res) => {
      this.allHomes = res;
      console.log(this.allHomes);
    });
    setTimeout(() => {

      this.getAllAlertsFromAllHomes();
    }, 1000);

  }

  ngOnInit() {
  }

  getAllAlertsFromAllHomes() {
    let homeId;
    for (let i = 0; i < this.allHomes.length; i++) {
      homeId = this.allHomes[i];
      this.alertsService.getAlertsByHome(homeId.id).subscribe((res) => {
        if(res != null) {
          this.allAlerts = [...this.allAlerts, ...res];
        }
      });
    }

    setTimeout(() => {
      console.log("all alerts " +this.allAlerts);
    },2000)


  }

}

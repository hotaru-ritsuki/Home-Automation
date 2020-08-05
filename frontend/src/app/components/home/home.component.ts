import {Component, OnInit} from '@angular/core';
import {Home} from '../../models/Home';
import {HomeService} from '../../services/home.service';
import {Router} from '@angular/router';
import {LocalDeviceService} from '../../services/local-device.service';
import {HomeSpecific} from '../../models/HomeSpecific';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  homes: Home[] = [];
  listHomes: HomeSpecific[] = [];

  constructor(private homeService: HomeService, private router: Router, private localDeviceService: LocalDeviceService) {
    this.homeService.getHomes().subscribe((res) => {
      this.homes = res;
      for (const homeElement of this.homes) {
        this.localDeviceService.findAllByHome(homeElement.id).subscribe((resu) => {
          const cos = new HomeSpecific();
          cos.id = homeElement.id;
          cos.city = homeElement.city;
          cos.addressa = homeElement.addressa;
          cos.name = homeElement.name;
          cos.deviceAmount = resu.length;
          this.listHomes.push(cos);
        });
      }
    });
    this.router.navigate(['administration/homes']);
  }

  ngOnInit() {
  }

}

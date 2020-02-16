import {Component, OnInit} from '@angular/core';
import {HomeService} from '../../../services/home.service';
import {Home} from '../../../models/Home';
import {Locations} from '../../../models/Locations';
import {Router} from '@angular/router';
import {LocalStorageService} from '../../../services/local-storage.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  locations: Locations[] = [];
  homes: Home[] = [];
  home: Home;
  areHomes = true;

  constructor(private homeService: HomeService, private router: Router,
              private localStorageService: LocalStorageService) {
    this.router.navigate(['dashboard']);
    this.homeService.getHomes().subscribe(res => {
      this.homes = res;
      this.areHomes = this.homes.length > 0;
    });
  }

  setCurrentHome(home: Home) {
    this.home = home;
    console.log(this.home);
  }

  ngOnInit(): void {
  }

  redirect() {
    setTimeout(() => {
      this.router.navigateByUrl('device/' + this.home.name + '/' + this.home.id + '/location/' + 0);
    }, 100);
  }
}

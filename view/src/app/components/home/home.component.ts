import {Component, OnInit} from '@angular/core';
import {Home} from '../../models/Home';
import {HomeService} from '../../services/home.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  homes: Home[] = [];
  home: Home;

  constructor(private homeService: HomeService, private router: Router) {
    this.homeService.getHomes().subscribe((res) => {
      this.homes = res;
      this.home = this.homes[0];
    });
    this.router.navigate(['administration/homes']);
  }

  ngOnInit() {
  }

}

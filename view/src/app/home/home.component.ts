import {Component, OnInit} from '@angular/core';
import {LocationService} from './service/location.service';
import {Locations} from './model/Locations';
import {Home} from './model/Home';
import {HomeService} from './service/home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  homes: Home[] = [];
  home: Home;

  constructor(private homeService: HomeService) {
  }

  ngOnInit() {
  }

  save(countryI: string, cityI: string, addressaI: string) {
    const answer = {
      country: countryI,
      city: cityI,
      addressa: addressaI
    };
    this.homeService.postHome(answer).subscribe();
  }

}

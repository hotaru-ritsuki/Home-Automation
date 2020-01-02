import {Component, OnInit} from '@angular/core';
import {HomeService} from '../service/home.service';
import {LocationService} from '../service/location.service';
import {Locations} from '../model/Locations';

@Component({
  selector: 'app-home-details',
  templateUrl: './home-details.component.html',
  styleUrls: ['./home-details.component.css']
})
export class HomeDetailsComponent implements OnInit {

  id;

  addLocation = false;

  home = {
    id: 0,
    country: ' ',
    city: '',
    addressa: '',
    locations: []
  };

  constructor(private homeService: HomeService, private locationService: LocationService) {
    this.homeService.id.subscribe((res) => {
      this.id = res;
      this.homeService.getHome(this.id).subscribe((asd) => {
        this.home = asd;
      });
    });
  }

  ngOnInit() {
  }

  save(nameI: string) {
    if (nameI !== '') {
      const answer = {
        name: nameI,
        homeId: this.id,
      };
      this.locationService.postLocation(answer).subscribe((res) => {
        if (this.home.locations !== null) {
          this.home.locations.push(res);
        } else {
          this.home.locations = [];
          this.home.locations.push(res);
        }
      });
      this.addLocation = false;
    }
  }

  chang() {
    this.addLocation = !this.addLocation;
  }

  editName(loc: Locations, str: string) {
    loc.name = str;
    this.locationService.putLocation(loc).subscribe();
  }

}

import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {ActivatedRoute, Router} from '@angular/router';
import {HomeService} from '../../services/home.service';
import {Home} from '../../models/Home';
import {NewHomeWarningComponent} from '../new-home-warning/new-home-warning.component';

@Component({
  selector: 'app-update-home',
  templateUrl: './update-home.component.html',
  styleUrls: ['./update-home.component.css']
})
export class UpdateHomeComponent implements OnInit {

  homeId: number;
  home: Home = new Home();

  constructor(public dialog: MatDialog, private router: Router, private homeService: HomeService, private route: ActivatedRoute,) {
  }

  ngOnInit() {
    this.homeId = this.route.snapshot.params['home'];
    this.homeService.getHome(this.homeId).subscribe((res) => {
      this.home = res;
    });
    this.router.navigate(['administration/homes/' + this.homeId + '/update']);
  }

  update(countryI: string, cityI: string, addressaI: string, nameI: string) {
    let checker = 1;
    if (countryI !== '' && cityI !== '' && addressaI !== '' && nameI !== '') {
      const answer = {
        id: this.homeId,
        name: nameI,
        country: countryI,
        city: cityI,
        addressa: addressaI
      };
      this.homeService.putHome(answer).subscribe((res) => {
        checker = 0;
        this.router.navigateByUrl('/device/' + nameI + '/' + this.homeId + '/location/0');
      });
    } else {
      this.openModal();
    }
    setTimeout(() => {
      if (checker === 1) {
        this.openModal1();
      }
    },350);
  }

  openModal() {
    const dialogRef = this.dialog.open(NewHomeWarningComponent, {
      data: {
        message: 'Fields: Name, Address, City must be filled out!',
      }
    });
  }

  openModal1() {
    const dialogRef = this.dialog.open(NewHomeWarningComponent, {
      data: {
        message: 'Address is in used.',
      }
    });
  }

}

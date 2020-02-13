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
  home: Home;

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
    if (countryI !== '' && cityI !== '' && addressaI !== '' && nameI !== '') {
      const answer = {
        id: this.homeId,
        name: nameI,
        country: countryI,
        city: cityI,
        addressa: addressaI
      };
      this.homeService.putHome(answer).subscribe((res) => {
        this.router.navigateByUrl('/device/' + nameI + '/' + this.homeId + '/location/0');
      });
    } else {
      this.openModal();
    }
  }

  openModal() {
    let dialogRef = this.dialog.open(NewHomeWarningComponent, {
      data: {
        name: 'Are you sure, you want to delete this device?',
      }
    });
  }

}

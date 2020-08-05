import {Component, OnInit} from '@angular/core';
import {HomeService} from '../../services/home.service';
import {Router} from '@angular/router';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {NewHomeWarningComponent} from '../new-home-warning/new-home-warning.component';

@Component({
  selector: 'app-new-home',
  templateUrl: './new-home.component.html',
  styleUrls: ['./new-home.component.css']
})
export class NewHomeComponent implements OnInit {

  matDialog: MatDialogRef<NewHomeWarningComponent>;

  constructor(private homeService: HomeService, private router: Router, public dialog: MatDialog) {
    this.router.navigate(['administration/homes/create']);
  }

  ngOnInit() {
  }

  save(countryI: string, cityI: string, addressaI: string, nameI: string) {
    let checker = 1;
    if (countryI !== '' && cityI !== '' && addressaI !== '' && nameI !== '') {
      const answer = {
        name: nameI,
        country: countryI,
        city: cityI,
        addressa: addressaI
      };
      this.homeService.postHome(answer).subscribe((res) => {
        checker = 0;
        this.router.navigateByUrl('/administration/homes');
      });
    } else {
      this.openModal();
    }
    setTimeout(() => {
      if (checker === 1) {
        this.openModal1();
      }
    }, 350);
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

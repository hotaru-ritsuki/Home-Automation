import {Component, OnInit} from '@angular/core';
import {HomeService} from '../../services/home.service';
import {Router} from '@angular/router';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {ModalComponent} from '../modal/modal.component';
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
    if (countryI !== '' && cityI !== '' && addressaI !== '' && nameI !== '') {
      const answer = {
        name: nameI,
        country: countryI,
        city: cityI,
        addressa: addressaI
      };
      this.homeService.postHome(answer).subscribe((res) => {
        this.router.navigateByUrl('administration/homes');
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

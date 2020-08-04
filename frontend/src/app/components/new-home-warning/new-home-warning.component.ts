import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-new-home-warning',
  templateUrl: './new-home-warning.component.html',
  styleUrls: ['./new-home-warning.component.css']
})
export class NewHomeWarningComponent implements OnInit {
  message: string;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    this.message = data.message;
  }

  ngOnInit() {
  }

}

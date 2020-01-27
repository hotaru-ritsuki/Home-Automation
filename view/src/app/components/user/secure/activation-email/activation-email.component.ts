import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-activation-email',
  templateUrl: './activation-email.component.html',
  styleUrls: ['./activation-email.component.css']
})
export class ActivationEmailComponent implements OnInit {
  constructor(
    private router: Router
  ) {
    this.router.navigate(['activationEmail']);
  }

  ngOnInit() {
  }


  }


import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {LocalStorageService} from './services/local-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  isSignedIn = false;

  constructor(private router: Router,
              private localStorageService: LocalStorageService) {
    this.router.navigate(['']);

  }

  ngOnInit(): void {
    if (this.localStorageService.getFirstName()) {
      this.isSignedIn = true;
    }
  }

}

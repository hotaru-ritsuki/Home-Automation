import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {LocalStorageService} from './services/local-storage.service';
import {MainService} from "./services/main.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(private router: Router,
              private localStorageService: LocalStorageService,private service:MainService) {
  }

  ngOnInit(): void {
  }

  isSignedIn(): boolean {
    if (this.localStorageService.getFirstName()) {
      return true;
    }
    return false;
  }

  logout() {
    this.localStorageService.clear()
    this.router.navigateByUrl("users/login");

  }
}

import {Component, OnInit} from '@angular/core';
import {MainService} from "../../../services/main.service";
import {Rule} from "../../../models/Rule";

@Component({
  selector: 'app-rule',
  templateUrl: './rule.component.html',
  styleUrls: ['./rule.component.css']
})
export class RuleComponent implements OnInit {
  rules: Rule[];

  constructor(private service: MainService) {
  }

  ngOnInit() {
    this.service.getRules().subscribe((res) => {
      this.rules = res;
    })
  }

}

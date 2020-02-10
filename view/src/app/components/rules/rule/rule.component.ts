import {Component, OnInit} from '@angular/core';
import {MainService} from "../../../services/main.service";
import {Rule} from "../../../models/Rule";
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-rule',
  templateUrl: './rule.component.html',
  styleUrls: ['./rule.component.css']
})
export class RuleComponent implements OnInit {
  rules: Rule[];

  constructor(private service: MainService, private router: Router) {
  }

  ngOnInit() {
    this.service.getRules().subscribe((res) => {
      this.rules = res;
    })
  }

  deleteRule(rule: Rule) {
    this.service.deleteRule(rule.id).subscribe((res) => {
        this.rules.splice(this.rules.indexOf(rule), 1)
      },
      (errors: HttpErrorResponse) => {
        console.log(errors.message);
      })
  }

  changeRuleStatus(rule: Rule) {
    this.service.changeStatus(rule).subscribe((res) => {
      console.log(res);
    })
  }

  edit(rule: Rule) {
    console.log(rule.actionRule);
    this.router.navigate(['rules/configure'], {
      queryParams: {
        id: rule.id,
        name: rule.name,
        conditions: rule.conditions,
        description: rule.description,
        actionRule: rule.actionRule,
        actions: rule.actionRule
      }
    });
  }
}

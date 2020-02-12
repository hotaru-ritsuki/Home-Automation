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
  rules: Rule[] = [];
  isEmpty  = false;

  constructor(private service: MainService, private router: Router) {
  }

  ngOnInit() {
    this.service.getRules().subscribe((res) => {
      if (res.length > 0){
        this.isEmpty = false
      }
      else {
        this.isEmpty = true;
      }
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
    if (rule.active === true) {
      rule.active = false;
    } else if (rule.active === false) {
      rule.active = true;
    }
    this.service.changeStatus(rule).subscribe((res) => {
    })
  }

  edit(rule: Rule) {
    let actions = [];
    for (let i = 0; i < arguments.length; i++) {
      let data = JSON.parse(rule.actionRule[i].actionSpecification);
      data['type'] = rule.actionRule[i].action;
      actions.push(data)
    }
    console.log(actions);
    this.router.navigate(['rules/configure'], {

      queryParams: {
        id: rule.id,
        name: rule.name,
        conditions: rule.conditions,
        description: rule.description,
        actions: JSON.stringify(actions)
      }
    });
  }
}

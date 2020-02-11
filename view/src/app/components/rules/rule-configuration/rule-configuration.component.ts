import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {MainService} from "../../../services/main.service";
import {LocalDevice} from "../../../models/LocalDevice";
import {FeatureDTO} from "../../../models/FeatureDTO";
import {ActivatedRoute} from "@angular/router";

export interface DialogData {
  conditions: string[]
}

@Component({
  selector: 'app-rule-configuration',
  templateUrl: './rule-configuration.component.html',
  styleUrls: ['./rule-configuration.component.css']
})
export class RuleConfigurationComponent implements OnInit {
  ruleName;
  ruleDescription;
  conditions: string[];
  fromData = [];
  actionData = [];


  constructor(public dialog: MatDialog, private service: MainService, private router: ActivatedRoute) {
  }


  ngOnInit() {
    this.router.queryParams.subscribe((res) => {
      console.log(res);
      if (Object.keys(res).length !== 0) {
        this.ruleName = res.name;
        this.ruleDescription = res.description;
        let cond = JSON.parse(res.conditions);
        for (let i = 0; i < cond.length; i++) {
          let fromDataObj = {
            state: cond[i].value, currentOperator: cond[i].operator,
            currentFeature: cond[i].field_name,
          };
          this.fromData.push(fromDataObj)
        }
      }
      this.service.getDevicesTypes().subscribe((res) => {
        this.conditions = res;
      })
    });
  }

  addCond() {
    const dialogRef = this.dialog.open(DialogCondition, {
      width: '400px',
      data: {conditions: this.conditions}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined)
        this.fromData.push(result);
    });
  }

  addAction() {
    const dialogRef = this.dialog.open(DialogAction, {
      width: '400px',
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined)
        this.actionData.push(result);
    });
  }

  saveRule() {
    this.service.saveRule(this.fromData, this.actionData, this.ruleName, this.ruleDescription).subscribe((res) => {
      console.log(res);
    })
  }


  deleteItem(item: any) {
    this.fromData.splice(this.fromData.indexOf(item), 1)
  }

  deleteAction(item: any) {
    this.actionData.splice(this.actionData.indexOf(item), 1)
  }

  getDataToShow(obj) {
    let name = Object.keys(obj)[0];
    let value = Object.keys(obj)[1];
    return obj[name] + ':' + obj[value];
  }
}

@Component({
  selector: 'dialog-condition',
  templateUrl: 'dialog-cond.html',
  styleUrls: ['./rule-configuration.component.css']
})
export class DialogCondition implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<DialogCondition>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData, private service: MainService) {
  }

  devices: LocalDevice[];
  operators = ['>', '<', '>=', '<=', '=', 'IN'];
  features: FeatureDTO[];
  fromData = {
    state: '',
    uuid: '',
    home_id:1,
    currentFeature: '',
    currentOperator: '',
    type: '',
    device: ''
  };

  onNoClick(): void {
    this.dialogRef.close();
  }


  changeType(container: HTMLDivElement, event, btn: HTMLButtonElement) {
    btn.disabled = false;
    this.devices = [];
    this.fromData.type = event;
    this.service.getAllLocalDevice().subscribe((res) => {
      for (const device of res) {
        if (device.deviceTemplate.type === event) {
          this.devices.push(device);
        }
      }
      container.style.display = 'block';
    })
  }

  getSpecification(state: HTMLDivElement, event: LocalDevice) {
    this.fromData.device = event.description;
    this.fromData.uuid = event.uuid;
    this.service.getSpecification(event.deviceTemplate.id).subscribe((res: FeatureDTO[]) => {
      this.features = res;
    });
    state.style.display = 'block'
  }

  ngOnInit(): void {
  }
}

@Component({
  selector: 'dialog-action',
  templateUrl: 'dialog-action.html',
  styleUrls: ['./rule-configuration.component.css']
})
export class DialogAction implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<DialogAction>,
    private service: MainService) {
  }

  actions = [];
  telegramData = {username: '', text: '', type: ''};
  emailData = {email: '', text: '', type: ''};
  deviceData = {name: '', data: '', type: ''};
  currentType = '';
  localDevices = [];


  onNoClick(): void {
    this.dialogRef.close();
  }

  changeType(event: any, telegramContainer: HTMLDivElement, emailContainer: HTMLDivElement, deviceContainer: HTMLDivElement, btn: HTMLButtonElement) {
    if (event.type === 'TELEGRAM') {
      this.currentType = 'telegram';
      this.telegramData.type = event;
      deviceContainer.style.display = 'none';
      emailContainer.style.display = 'none';
      telegramContainer.style.display = 'block';
    }
    if (event.type === 'MAIL') {
      this.currentType = 'mail';
      this.emailData.type = event;
      deviceContainer.style.display = 'none';
      telegramContainer.style.display = 'none';
      emailContainer.style.display = 'block';
    }
    if (event.type === 'DEVICE' || event.type === 'LOGGER') {
      this.currentType = 'device';
      this.deviceData.type = event;
      telegramContainer.style.display = 'none';
      emailContainer.style.display = 'none';
      deviceContainer.style.display = 'block'
    }
    btn.disabled = false;

  }

  ngOnInit(): void {
    this.service.getActions().subscribe((res) => {
      for (let i = 0; i < res.length; i++) {
        this.actions.push(res[i])
      }
    });


    this.service.getAllLocalDevice().subscribe((res) => {
      for (const device of res) {
        this.localDevices.push(device.description);
      }
    })
  }
}

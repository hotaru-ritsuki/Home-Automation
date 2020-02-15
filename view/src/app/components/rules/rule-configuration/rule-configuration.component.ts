import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {MainService} from "../../../services/main.service";
import {LocalDevice} from "../../../models/LocalDevice";
import {FeatureDTO} from "../../../models/FeatureDTO";
import {ActivatedRoute, Router} from "@angular/router";
import {Rule} from "../../../models/Rule";

export interface DialogData {
  conditions: string[]
  home_id: number
}

@Component({
  selector: 'app-rule-configuration',
  templateUrl: './rule-configuration.component.html',
  styleUrls: ['./rule-configuration.component.css']
})
export class RuleConfigurationComponent implements OnInit {
  typeOfSave = 'Save';
  ruleName = '';
  ruleDescription= '';
  ruleId;
  conditions: string[];
  fromData = [];
  actionData = [];
  actionsToDelete = [];
  homeId;

  constructor(public dialog: MatDialog, private service: MainService, private rout: Router, private router: ActivatedRoute) {
  }


  ngOnInit() {
    this.router.queryParams.subscribe((res) => {
      if (Object.keys(res).length !== 0) {
        this.typeOfSave = 'Update';
        this.ruleName = res.name;
        this.ruleId = res.id;
        this.ruleDescription = res.description;
        let cond = JSON.parse(res.conditions);
        for (let i = 0; i < cond.length; i++) {
          this.service.getDeviceByUuid(cond[i].device.uuid).subscribe((res) => {
            let fromDataObj = {
              deviceName: res.description,
              state: cond[i].value, currentOperator: cond[i].operator,
              currentFeature: cond[i].field_name,
              device: {uuid: cond[i].device.uuid, home_id: cond[i].device.home_id}
            };
            this.fromData.push(fromDataObj)
          });
        }
        this.actionData = JSON.parse(res.actions);
      }
      this.homeId = this.rout.url.split("/")[2];
      this.service.getDevicesTypes().subscribe((res) => {
        this.conditions = res;
      });
    });
  }

  addCond() {
    console.log(this.homeId);
    const dialogRef = this.dialog.open(DialogCondition, {
      width: '400px',
      data: {conditions: this.conditions, home_id: this.homeId}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined)
        this.fromData.push(result);
    });
  }

  addAction() {
    const dialogRef = this.dialog.open(DialogAction, {
      width: '400px',
      data: {conditions: this.conditions, home_id: this.homeId}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined)
        this.actionData.push(result);
    });
  }

  saveRule() {
    for (let i = 0; i < this.actionsToDelete.length; i++) {
      this.service.deleteActionRule(this.actionsToDelete[i], this.ruleId).subscribe((res) => {
      })
    }
    if (this.typeOfSave === 'Save') {
      this.service.saveRule(this.fromData, this.ruleName, this.ruleDescription).subscribe((res: Rule) => {
        for (let i = 0; i < this.actionData.length; i++) {
          this.service.saveRuleAction(res.id, this.actionData[i]).subscribe((res) => {
          })
        }
      })
    } else if (this.typeOfSave === 'Update') {
      this.service.updateRule(this.ruleId, this.fromData, this.ruleName, this.ruleDescription).subscribe((res: Rule) => {
        for (let i = 0; i < this.actionData.length; i++) {
          this.service.updateRuleAction(res.id, this.actionData[i]).subscribe((res) => {
          })
        }
      })
    }
    this.rout.navigate(['/rules/' + this.homeId])
  }


  deleteItem(item: any) {
    this.fromData.splice(this.fromData.indexOf(item), 1)
  }

  deleteAction(item: any) {
    this.actionsToDelete.push(item);
    this.actionData.splice(this.actionData.indexOf(item), 1)
  }

  getDataToShow(obj) {
    let name = Object.keys(obj)[0];
    let value = Object.keys(obj)[1];
    return obj[name] + ' : ' + obj[value];
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

  devices:LocalDevice[];
  operators = [];
  features: FeatureDTO[];
  fromData = {
    state: '',
    uuid: '',
    home_id: '',
    currentFeature: '',
    currentOperator: '',
    type: '',
    deviceName: ''
  };

  onNoClick(): void {
    this.dialogRef.close();
  }


  changeType(container: HTMLDivElement, event) {
    this.devices = [];
    this.fromData.type = event;
    console.log(this.data);
    this.service.getAllLocalDevice(this.data.home_id).subscribe((res) => {
      for (const device of res) {
        if (device.deviceTemplate.type === event) {
          this.devices.push(device);
        }
      }
      container.style.display = 'block';
    })
  }

  getSpecification(state: HTMLDivElement, event: LocalDevice, btn) {
    btn.disabled = false;
    this.fromData.deviceName = event.description;
    this.fromData.uuid = event.uuid;
    this.service.getSpecification(event.deviceTemplate.id).subscribe((res: FeatureDTO[]) => {
      this.features = res;
    });
    state.style.display = 'block'
  }

  ngOnInit(): void {
  }

  changeFeature($event: FeatureDTO) {
    if(JSON.parse($event.specification).type === 'numeric'){
      this.operators = ['>', '<', '>=', '<=', '=', 'IN']
    }
    if(JSON.parse($event.specification).type === 'enum'){
      this.operators = ['=','IN']
    }
    this.fromData.currentFeature = $event.featureDTO.name;
  }

  changeOperator($event: any) {
    if ($event == '=') 
    this.fromData.currentOperator = $event
  }
}

@Component({
  selector: 'dialog-action',
  templateUrl: 'dialog-action.html',
  styleUrls: ['./rule-configuration.component.css']
})
export class DialogAction implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    public dialogRef: MatDialogRef<DialogAction>,
    private service: MainService) {
  }

  actions = [];
  telegramData = {username: '', text: '', type: ''};
  emailData = {email: '', text: '', type: ''};
  deviceData = {uuid: '', data: '', type: ''};
  currentType = '';
  localDevices = [];
  telegramUsers = [];

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
    this.service.getTelegramUsersByHomeId(this.data.home_id).subscribe((res:any[]) => {
      for (let i = 0; i < res.length; i++) {
        this.telegramUsers.push(res[i].telegramUser.username)
      }
    });

    this.service.getActions().subscribe((res) => {
      for (let i = 0; i < res.length; i++) {
        this.actions.push(res[i])
      }
    });


    this.service.getAllLocalDevice(this.data.home_id).subscribe((res) => {
      for (const device of res) {
        this.localDevices.push({uuid: device.uuid, desc: device.description});
      }
    })
  }
}

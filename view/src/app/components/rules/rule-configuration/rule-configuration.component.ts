import {Component, Inject, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {MainService} from "../../../services/main.service";
import {LocalDevice} from "../../../models/LocalDevice";
import {FeatureDTO} from "../../../models/FeatureDTO";

export interface DialogData {
  conditions: string[]
}

export class NgbdModalContent {
  @Input() name;

  constructor(public activeModal: NgbActiveModal) {
  }
}

@Component({
  selector: 'app-rule-configuration',
  templateUrl: './rule-configuration.component.html',
  styleUrls: ['./rule-configuration.component.css']
})
export class RuleConfigurationComponent implements OnInit {
  public model;
  conditions: string[];
  fromData = [];


  constructor(public dialog: MatDialog, private service: MainService) {
  }

  ngOnInit() {
    this.service.getDevicesTypes().subscribe((res) => {
      this.conditions = res;
    })
  }

  save() {
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '400px',
      data: {conditions: this.conditions}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined)
        this.fromData.push(result);
    });
  }
}

@Component({
  selector: 'dialog-overview-example-dialog',
  templateUrl: 'dialog.html',
  styleUrls: ['./rule-configuration.component.css']
})
export class DialogOverviewExampleDialog {

  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData, private service: MainService) {
  }

  devices: LocalDevice[];
  operators = ['>', '<', '>=', '<=', '=', 'IN'];
  features: FeatureDTO[];
  fromData = {
    state: '',
    uuid: '',
    currentFeature: '',
    currentOperator: '',
    type: ''
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
    this.fromData.uuid = event.uuid;
    this.service.getSpecification(event.deviceTemplate.id).subscribe((res: FeatureDTO[]) => {
      this.features = res;
    });
    state.style.display = 'block'
  }
}

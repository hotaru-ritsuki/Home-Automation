import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {DateTimePickerModule} from '@syncfusion/ej2-angular-calendars';
import {FormsModule} from '@angular/forms';
import {AppComponent} from './app.component';
import {DevicesGraphicsComponent} from './components/devices-graphics/devices-graphics.component';
import {ChartsModule} from 'ng2-charts';
import {GraphicsDashbordComponent} from './components/graphics-dashbord/graphics-dashbord.component';
import {DevicesComponent} from './components/local-device/devices.component';
import {DeviceTemplateComponent} from './components/device-template/device-template.component';
import {InterceptorService} from './services/intercept.service';
import {SignUpComponent} from './components/user/secure/sign-up/sign-up.component';
import {LogInComponent} from './components/user/secure/log-in/log-in.component';
import {ActivationEmailComponent} from './components/user/secure/activation-email/activation-email.component';
import {AuthGuardService} from "./services/auth-guard.service";
import {DashboardComponent} from './components/dashboard/dashboard/dashboard.component';
import {DashboardLocationsComponent} from './components/dashboard/dashboard-locations/dashboard-locations.component';
import {ButtonsModule, MDBBootstrapModule, NavbarModule, WavesModule} from 'angular-bootstrap-md';
import {LightToggleComponent} from './components/dashboard/light-toggle/light-toggle.component';
import {SliderModule} from 'angular-image-slider';
import {RuleComponent} from './components/rules/rule/rule.component';
import {
  DialogAction, DialogCondition,
  RuleConfigurationComponent
} from './components/rules/rule-configuration/rule-configuration.component';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {MatButtonModule, MatDialogModule, MatInputModule, MatSelectModule} from "@angular/material";

const routes: Routes = [
  {path: 'statistic', component: GraphicsDashbordComponent, canActivate: [AuthGuardService]},
  {path: 'rules', component: RuleComponent},
  {path: 'rules/configure', component: RuleConfigurationComponent},
  {path: '', component: DashboardComponent},
  {path: 'device', component: DevicesComponent, canActivate: [AuthGuardService]},
  {path: 'locations', component: DashboardLocationsComponent, canActivate: [AuthGuardService]},
  {path: 'login', component: LogInComponent},
  {path: 'register', component: SignUpComponent},
  {path: 'device-template', component: DeviceTemplateComponent, canActivate: [AuthGuardService]}
];

@NgModule({

  declarations: [
    AppComponent,
    DevicesComponent,
    DevicesGraphicsComponent,
    GraphicsDashbordComponent,
    DeviceTemplateComponent,
    LogInComponent,
    SignUpComponent,
    ActivationEmailComponent,
    DashboardComponent,
    LightToggleComponent,
    DashboardLocationsComponent,
    RuleComponent,
    RuleConfigurationComponent,
    DialogAction,
    DialogCondition
  ],
  entryComponents: [DialogCondition,DialogAction],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ChartsModule,
    FormsModule,
    DateTimePickerModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes),
    NavbarModule,
    MDBBootstrapModule.forRoot(),
    NgbModule,
    WavesModule,
    SliderModule,
    ButtonsModule,
    MatSelectModule,
    MatDialogModule,
    MatInputModule,
    MatButtonModule
  ],


  providers:
    [{
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorService,
      multi: true
    }],
  bootstrap:
    [AppComponent]
})
export class AppModule {
}

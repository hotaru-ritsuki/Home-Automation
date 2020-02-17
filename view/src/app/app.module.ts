import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {AppComponent} from './app.component';
import {DevicesGraphicsComponent} from './components/devices-graphics/devices-graphics.component';
import {ChartsModule} from 'ng2-charts';
import {GraphicsDashbordComponent} from './components/graphics-dashbord/graphics-dashbord.component';
import {DevicesComponent} from './components/local-device/devices.component';
import {DeviceTemplateComponent} from './components/device-template/device-template.component';
import {InterceptorService} from './services/intercept.service';
import {AuthGuardService} from './services/auth-guard.service';
import {DashboardComponent} from './components/dashboard/dashboard/dashboard.component';
import {DashboardLocationsComponent} from './components/dashboard/dashboard-locations/dashboard-locations.component';
import {ButtonsModule, MDBBootstrapModule, NavbarModule, WavesModule} from 'angular-bootstrap-md';
import {LightToggleComponent} from './components/dashboard/light-toggle/light-toggle.component';
import {SliderModule} from 'angular-image-slider';
import {RuleComponent} from './components/rules/rule/rule.component';
import {
  DialogAction,
  DialogCondition,
  RuleConfigurationComponent
} from './components/rules/rule-configuration/rule-configuration.component';
import {MatButtonModule, MatDialogModule, MatInputModule, MatSelectModule} from '@angular/material';
import {ChangePasswordComponent} from './components/user/change-password/change-password.component';
import {ConfirmRegistrationComponent} from './components/user/confirm-registration/confirm-registration.component';
import {UserComponent} from './components/user/user.component';
import {ResendRegistrationTokenComponent} from './components/user/resend-registration-token/resend-registration-token.component';
import {RestorePasswordComponent} from './components/user/restore-password/restore-password/restore-password.component';
import {ModalComponent} from './components/modal/modal.component';
import {RestorePasswordPart2Component} from './components/restore-password-part2/restore-password-part2.component';
import {AddLocalDeviceComponent} from "./components/add-local-device/add-local-device.component";
import {LocationModalComponent} from "./components/location-modal/location-modal.component";
import {LogInComponent} from "./components/user/log-in/log-in.component";
import {SignUpComponent} from "./components/user/sign-up/sign-up.component";
import { HomeComponent } from './components/home/home.component';
import { NewHomeComponent } from './components/new-home/new-home.component';
import { NewHomeWarningComponent } from './components/new-home-warning/new-home-warning.component';
import { UpdateLocationComponent } from './components/update-location/update-location.component';
import { UpdateHomeComponent } from './components/update-home/update-home.component';
import {DateTimePickerModule} from '@syncfusion/ej2-angular-calendars';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {UserInformationComponent,FormatTimePipe} from "./components/user/user-information/user-information.component";
import {LoggedInGuardService} from "./services/logged-in-guard.service";
import { DeleteLocationComponent } from './components/delete-location/delete-location.component';
import {GraphicDialogComponent} from "./components/dashboard/graphic-dialog/graphic-dialog.component";

const routes: Routes = [
  {path: '', component: DashboardComponent, canActivate: [AuthGuardService]},
  {path: 'statistic', component: GraphicsDashbordComponent, canActivate: [AuthGuardService]},
  {path: 'rules/:home_id', component: RuleComponent, canActivate: [AuthGuardService]},
  {path: 'rules/:home_id/configure', component: RuleConfigurationComponent, canActivate: [AuthGuardService]},
  {path: 'device/:home_name/:home/location/:location', component: DevicesComponent},
  {path: 'locations', component: DashboardLocationsComponent, canActivate: [AuthGuardService]},
  {path: 'device-template/:home_name/:home/location/:location', component: DeviceTemplateComponent, canActivate: [AuthGuardService]},
  {path: 'add-local-device/:home_name/:home/:location/:device/:brand/:model', component: AddLocalDeviceComponent, canActivate: [AuthGuardService]},
  {path: 'device-modal', component: ModalComponent, canActivate: [AuthGuardService]},
  {path: 'delete-location', component: DeleteLocationComponent, canActivate: [AuthGuardService]},
  {path: 'update-location/:id', component: UpdateLocationComponent, canActivate: [AuthGuardService]},
  {path: 'location-modal/home_name', component: LocationModalComponent, canActivate: [AuthGuardService]},
  {path: 'administration/homes', component: HomeComponent, canActivate: [AuthGuardService]},
  {path: 'administration/homes/create', component: NewHomeComponent, canActivate: [AuthGuardService]},
  {path: 'administration/homes/create/error', component: NewHomeWarningComponent, canActivate: [AuthGuardService]},
  {path: 'administration/homes/:home/update', component: UpdateHomeComponent, canActivate: [AuthGuardService]},
  {
    path: 'users',
    component: UserComponent,
    children: [
      {path: 'restorePassword/:id/:token', component: RestorePasswordPart2Component,canActivate:[LoggedInGuardService]},
      {path: 'login', component: LogInComponent,canActivate:[LoggedInGuardService]},
      {path: 'register', component: SignUpComponent,canActivate:[LoggedInGuardService]},
      {path: 'confirmRegistration/:token', component: ConfirmRegistrationComponent,canActivate:[LoggedInGuardService]},
      {path: 'changePassword', component: ChangePasswordComponent, canActivate: [AuthGuardService]},
      {path: 'resendRegistrationToken', component: ResendRegistrationTokenComponent,canActivate:[LoggedInGuardService]},
      {path: 'restore', component: RestorePasswordComponent,canActivate:[LoggedInGuardService]},
      {path: 'userInfo', component: UserInformationComponent,canActivate:[AuthGuardService]},
    ]
  },
  {path: 'dashboard', component: DashboardComponent,canActivate:[AuthGuardService]},
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
    DashboardComponent,
    LightToggleComponent,
    DashboardLocationsComponent,
    AddLocalDeviceComponent,
    RuleComponent,
    RuleConfigurationComponent,
    DialogAction,
    DialogCondition,
    ChangePasswordComponent,
    ConfirmRegistrationComponent,
    ResendRegistrationTokenComponent,
    UserComponent,
    RestorePasswordComponent,
    ModalComponent,
    RestorePasswordPart2Component,
    LocationModalComponent,
    HomeComponent,
    NewHomeComponent,
    NewHomeWarningComponent,
    UpdateLocationComponent,
    UpdateHomeComponent,
    UserInformationComponent,
    FormatTimePipe,
    DeleteLocationComponent,
  ],
  entryComponents: [DialogCondition, DialogAction, GraphicDialogComponent],
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
    MatButtonModule,
    NgbModule,
    MatDialogModule
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

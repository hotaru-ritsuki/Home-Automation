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
import {AuthGuardService} from './services/auth-guard.service';
import {SignUpComponent} from './components/user/sign-up/sign-up.component';
import {LogInComponent} from './components/user/log-in/log-in.component';
import {DashboardComponent} from './components/dashboard/dashboard/dashboard.component';
import {DashboardLocationsComponent} from './components/dashboard/dashboard-locations/dashboard-locations.component';
import {ButtonsModule, MDBBootstrapModule, NavbarModule, WavesModule} from 'angular-bootstrap-md';
import {LightToggleComponent} from './components/dashboard/light-toggle/light-toggle.component';
import {RuleComponent} from './components/rules/rule/rule.component';
import {
  DialogAction,
  DialogCondition,
  RuleConfigurationComponent
} from './components/rules/rule-configuration/rule-configuration.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {MatButtonModule, MatDialogModule, MatInputModule, MatSelectModule} from '@angular/material';
import {ChangePasswordComponent} from './components/user/change-password/change-password.component';
import {ConfirmRegistrationComponent} from './components/user/confirm-registration/confirm-registration.component';
import {UserComponent} from './components/user/user.component';
import {UserInformationComponent, FormatTimePipe} from './components/user/user-information/user-information.component';
import {ResendRegistrationTokenComponent} from './components/user/resend-registration-token/resend-registration-token.component';
import {RestorePasswordComponent} from './components/user/restore-password/restore-password/restore-password.component';
import {ModalComponent} from './components/modal/modal.component';
import {RestorePasswordPart2Component} from './components/restore-password-part2/restore-password-part2.component';

const routes: Routes = [
  {path: 'statistic', component: GraphicsDashbordComponent},
  {path: 'rules', component: RuleComponent},
  {path: 'rules/configure', component: RuleConfigurationComponent},
  {path: 'device', component: DevicesComponent},
  {path: 'locations', component: DashboardLocationsComponent},
  {path: 'device-template', component: DeviceTemplateComponent},
  {path: 'users/restorePassword/:id/:token', component: RestorePasswordPart2Component},
  {path: 'device-modal', component: ModalComponent},
  {
    path: 'users',
    component: UserComponent,
    children: [
      { path: 'login', component: LogInComponent},
      { path: 'register', component: SignUpComponent},
      { path: 'confirmRegistration', component: ConfirmRegistrationComponent},
      { path: 'changePassword', component: ChangePasswordComponent},
      { path: 'resendRegistrationToken', component: ResendRegistrationTokenComponent},
      { path: 'userInfo', component: UserInformationComponent},
      { path: 'restore', component: RestorePasswordComponent}
    ]
  },
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
    RuleComponent,
    RuleConfigurationComponent,
    DialogAction,
    DialogCondition,
    ChangePasswordComponent,
    ConfirmRegistrationComponent,
    ResendRegistrationTokenComponent,
    UserComponent,
    UserInformationComponent,
    FormatTimePipe,
    RestorePasswordComponent,
    ModalComponent,
    RestorePasswordPart2Component,
  ],
  entryComponents: [DialogCondition, DialogAction],
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

import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {DateTimePickerModule} from '@syncfusion/ej2-angular-calendars';
import {FormsModule} from '@angular/forms';
import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {HomeDetailsComponent} from './home/home-details/home-details.component';
import {DevicesGraphicsComponent} from './components/devices-graphics/devices-graphics.component';
import {ChartsModule} from 'ng2-charts';
import {GraphicsDashbordComponent} from './components/graphics-dashbord/graphics-dashbord.component';
import {MainComponent} from './components/main/main.component';
import {DevicesComponent} from './components/local-device/devices.component';
import {DeviceTemplateComponent} from './components/device-template/device-template.component';
import {InterceptorService} from './services/intercept.service';
import {SignUpComponent} from './components/user/sign-up/sign-up.component';
import {LogInComponent} from './components/user/log-in/log-in.component';
import {AuthGuardService} from './services/auth-guard.service';
import {HomePageGuardService} from './services/homepage-guard.service';
import {DashboardComponent} from './components/dashboard/dashboard/dashboard.component';
import {DashboardLocationsComponent} from './components/dashboard/dashboard-locations/dashboard-locations.component';
import {ButtonsModule, MDBBootstrapModule, NavbarModule, WavesModule} from 'angular-bootstrap-md';
import {LightToggleComponent} from './components/dashboard/light-toggle/light-toggle.component';
import {SliderModule} from 'angular-image-slider';
import { ChangePasswordComponent } from './components/user/change-password/change-password.component';
import {ConfirmRegistrationComponent} from './components/user/confirm-registration/confirm-registration.component';
import {UserComponent} from './components/user/user.component';
import {ResendRegistrationTokenComponent} from './components/user/resend-registration-token/resend-registration-token.component'
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { RestorePasswordComponent } from './components/user/restore-password/restore-password/restore-password.component';
import {MatDialogModule} from "@angular/material/dialog";
import {ModalComponent} from './components/modal/modal.component';

const routes: Routes = [
  {path: 'statistic', component: GraphicsDashbordComponent, canActivate: [AuthGuardService]},
  {path: 'home', component: HomeComponent, canActivate: [AuthGuardService]},
  {path: 'device', component: DevicesComponent, canActivate: [AuthGuardService]},
  {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuardService]},
  {path: 'locations', component: DashboardLocationsComponent, canActivate: [AuthGuardService]},
  {path: 'device-template', component: DeviceTemplateComponent, canActivate: [AuthGuardService]},
  {path: 'device-modal', component: ModalComponent, canActivate: [AuthGuardService]},
  {path: 'users',
    component: UserComponent,
    children: [
      {path: 'login', component: LogInComponent},
      {path: 'register', component: SignUpComponent},
      {path: 'confirmRegistration', component: ConfirmRegistrationComponent, canActivate: [AuthGuardService] },
      {path: 'changePassword', component: ChangePasswordComponent, canActivate: [AuthGuardService]},
      {path: 'resendRegistrationToken', component: ResendRegistrationTokenComponent},
      {path: 'restore', component: RestorePasswordComponent}
    ]
  },
  {path: '', component: MainComponent, canActivate: [HomePageGuardService]}
];

@NgModule({

  declarations: [
    AppComponent,
    HomeComponent,
    HomeDetailsComponent,
    DevicesComponent,
    DevicesGraphicsComponent,
    GraphicsDashbordComponent,
    MainComponent,
    DeviceTemplateComponent,
    LogInComponent,
    SignUpComponent,
    DashboardComponent,
    LightToggleComponent,
    DashboardLocationsComponent,
    ChangePasswordComponent,
    ConfirmRegistrationComponent,
    ResendRegistrationTokenComponent,
    UserComponent,
    RestorePasswordComponent,
    ModalComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ChartsModule,
    FormsModule,
    DateTimePickerModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes, { enableTracing: true }),
    NavbarModule,
    MDBBootstrapModule.forRoot(),
    WavesModule,
    SliderModule,
    ButtonsModule,
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

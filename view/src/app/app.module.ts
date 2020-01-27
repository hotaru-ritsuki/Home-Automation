import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {DateTimePickerModule} from '@syncfusion/ej2-angular-calendars';
import {FormsModule} from "@angular/forms";
import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {HomeDetailsComponent} from './home/home-details/home-details.component';
import {DevicesGraphicsComponent} from './components/devices-graphics/devices-graphics.component';
import {ChartsModule} from "ng2-charts";
import {GraphicsDashbordComponent} from './components/graphics-dashbord/graphics-dashbord.component';
import {MainComponent} from './components/main/main.component';
import {DevicesComponent} from "./components/local-device/devices.component";
import {DeviceTemplateComponent} from './components/device-template/device-template.component';
import {InterceptorService} from './services/intercept.service';
import {SignUpComponent} from './components/user/secure/sign-up/sign-up.component';
import {LogInComponent} from './components/user/secure/log-in/log-in.component';
import {ActivationEmailComponent} from './components/user/secure/activation-email/activation-email.component';

const routes: Routes = [
  {path: 'statistic', component: GraphicsDashbordComponent},
  {path: '', component: MainComponent},
  {path: 'home', component: HomeComponent},
  {path: 'device', component: DevicesComponent},
  {path: 'device-template', component: DeviceTemplateComponent},
  {path: 'login', component: LogInComponent},
  {path: 'register', component: SignUpComponent},
  {path: 'activationEmail', component: ActivationEmailComponent}
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
    ActivationEmailComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ChartsModule,
    FormsModule,
    DateTimePickerModule,
    RouterModule.forRoot(routes)
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

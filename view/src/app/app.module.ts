import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {HomeDetailsComponent} from './home/home-details/home-details.component';
import {DevicesGraphicsComponent} from './components/devices-graphics/devices-graphics.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {ChartsModule} from 'ng2-charts';
import {GraphicsDashbordComponent} from './components/graphics-dashbord/graphics-dashbord.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {DateTimePickerModule} from '@syncfusion/ej2-angular-calendars';
import {RouterModule, Routes} from '@angular/router';
import {MainComponent} from './components/main/main.component';
import {DevicesComponent} from './components/local-device/devices.component';
import {InterceptorService} from './services/intercept.service';
import {SignUpComponent} from './components/user/secure/sign-up/sign-up.component';
import {LogInComponent} from './components/user/secure/log-in/log-in.component';
import {FormsModule} from '@angular/forms';
import {ActivationEmailComponent} from './components/user/secure/activation-email/activation-email.component';

const routes: Routes = [
  {path: 'statistic', component: GraphicsDashbordComponent},
  {path: '', component: MainComponent},
  {path: 'home', component: HomeComponent},
  {path: 'device', component: DevicesComponent},
  {path: 'login', component: LogInComponent},
  {path: 'register', component: SignUpComponent},
  {path: 'activationEmail', component: ActivationEmailComponent}
  // {path: 'auth',
  //   component: SecureComponent,
  //   children: [
  //     { path: '', component: SignInComponent },
  //     { path: 'sign-up', component: SignUpComponent },
  //     { path: 'activation-email', component: ActivationEmailComponent }
  //   ]
  // }
];

@NgModule({

  declarations: [
    AppComponent,
    HomeComponent,
    HomeDetailsComponent,
    DevicesGraphicsComponent,
    GraphicsDashbordComponent,
    LogInComponent,
    SignUpComponent,
    DevicesComponent,
    ActivationEmailComponent,
    MainComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ChartsModule,
    DateTimePickerModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes),
    FormsModule,
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: InterceptorService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}

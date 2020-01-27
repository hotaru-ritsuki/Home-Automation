import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {HomeDetailsComponent} from './home/home-details/home-details.component';
import {DevicesGraphicsComponent} from './components/devices-graphics/devices-graphics.component';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule, Routes} from '@angular/router';
import {GraphicsDashbordComponent} from './components/graphics-dashbord/graphics-dashbord.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {DateTimePickerModule} from '@syncfusion/ej2-angular-calendars';
import {MainComponent} from './components/main/main.component';
import {DevicesComponent} from './components/local-device/devices.component';
import {FormsModule} from '@angular/forms';
import {DashboardComponent} from './components/dashboard/dashboard/dashboard.component';
import {DashboardLocationsComponent} from './components/dashboard/dashboard-locations/dashboard-locations.component';
import {MDBBootstrapModule, NavbarModule, WavesModule} from 'angular-bootstrap-md';
import {ChartsModule} from 'ng2-charts';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {LightToggleComponent} from './components/dashboard/light-toggle/light-toggle.component';
import {SliderModule} from 'angular-image-slider';

const routes: Routes = [
  {path: 'statistic', component: GraphicsDashbordComponent},
  {path: '', component: MainComponent},
  {path: 'home', component: HomeComponent},
  {path: 'device', component: DevicesComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'locations', component: DashboardLocationsComponent}
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
    DashboardComponent,
    DashboardLocationsComponent,
    LightToggleComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ChartsModule,
    FormsModule,
    DateTimePickerModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes),
    WavesModule,
    NavbarModule,
    MDBBootstrapModule.forRoot(),
    ChartsModule,
    NgbModule,
    SliderModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}

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
import {DashboardGraphicsComponent} from './components/dashboard/dashboard-graphics/dashboard-graphics.component';
import {WavesModule, NavbarModule, MDBBootstrapModule} from 'angular-bootstrap-md';
import {ChartsModule} from 'ng2-charts';

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
    DashboardGraphicsComponent,
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
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}

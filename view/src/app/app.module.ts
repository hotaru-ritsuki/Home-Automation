import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HomeComponent} from './components/home/home.component';
import {HomeDetailsComponent} from './components/home-details/home-details.component';
import {DevicesGraphicsComponent} from './components/devices-graphics/devices-graphics.component';
import {HttpClientModule} from '@angular/common/http';
import {ChartsModule} from "ng2-charts";
import {RouterModule, Routes} from '@angular/router';
import {GraphicsDashbordComponent} from './components/graphics-dashbord/graphics-dashbord.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {DateTimePickerModule} from '@syncfusion/ej2-angular-calendars';
import {MainComponent} from './components/main/main.component';
<<<<<<< HEAD
import {LocationsComponent} from './components/locations/locations.component';
=======
import {DevicesComponent} from "./components/local-device/devices.component";
import {FormsModule} from "@angular/forms";
>>>>>>> e0d68ebb3be929cd23a3f56dd495e0a2c849d7d1

const routes: Routes = [
  {path: '', component: MainComponent},
  {path: 'statistic', component: GraphicsDashbordComponent},
  {
    path: 'home/:id', component: HomeComponent, children:
      [{path: 'location/:id',component: LocationsComponent}]
  },
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
    LocationsComponent
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
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}

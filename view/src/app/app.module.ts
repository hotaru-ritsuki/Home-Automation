import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HomeComponent} from './components/home/home.component';
import {HomeDetailsComponent} from './components/home-details/home-details.component';
import {DevicesGraphicsComponent} from './components/devices-graphics/devices-graphics.component';
import {HttpClientModule} from '@angular/common/http';
import {ChartsModule} from "ng2-charts";
import {GraphicsDashbordComponent} from './components/graphics-dashbord/graphics-dashbord.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {DateTimePickerModule} from '@syncfusion/ej2-angular-calendars';
import {RouterModule, Routes} from '@angular/router';
import {MainComponent} from './components/main/main.component';
import {LocationsComponent} from './components/locations/locations.component';

const routes: Routes = [
  {path: '', component: MainComponent},
  {path: 'statistic', component: GraphicsDashbordComponent},
  {
    path: 'home/:id', component: HomeComponent, children: [{path: 'location/:id'}]
  },
];

@NgModule({

  declarations: [
    AppComponent,
    HomeComponent,
    HomeDetailsComponent,
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
    DateTimePickerModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}

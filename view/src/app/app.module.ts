import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { DevicesGraphicsComponent } from './components/devices-graphics/devices-graphics.component';
import {HttpClientModule} from '@angular/common/http';
import {ChartsModule} from "ng2-charts";
import { GraphicsDashbordComponent } from './components/graphics-dashbord/graphics-dashbord.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {DateTimePickerModule} from "@syncfusion/ej2-angular-calendars";
import {RouterModule, Routes} from "@angular/router";
import { MainComponent } from './components/main/main.component';

const routes: Routes = [
  {path: 'statistic', component: GraphicsDashbordComponent},
  {path: '', component: MainComponent},
];

@NgModule({

  declarations: [
    AppComponent,
    DevicesGraphicsComponent,
    GraphicsDashbordComponent,
    MainComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ChartsModule,
    DateTimePickerModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

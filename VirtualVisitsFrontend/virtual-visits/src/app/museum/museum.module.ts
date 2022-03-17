import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MuseumRoutingModule } from './museum-routing.module';
import { MuseumContainerComponent } from './museum-container/museum-container.component';
import { MuseumDetailsContainerComponent } from './museum-details-container/museum-details-container.component';
import { AppMaterialModule } from '../app-material/app-material.module';
import { WeatherForecastContainerComponent } from './weather-forecast-container/weather-forecast-container.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MuseumListComponent } from './museum-list/museum-list.component';
import { MapContainerComponent } from './map-container/map-container.component';
import { GoogleMapsModule } from '@angular/google-maps';
import { MatDialogModule } from '@angular/material/dialog';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    MuseumContainerComponent,
    MuseumDetailsContainerComponent,
    WeatherForecastContainerComponent,
    MuseumListComponent,
    MapContainerComponent
  ],
  imports: [
    CommonModule,
    MuseumRoutingModule,
    FlexLayoutModule,
    AppMaterialModule,
    GoogleMapsModule,
    MatDialogModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [
    MuseumContainerComponent,
    MuseumDetailsContainerComponent
  ]
})
export class MuseumModule { }

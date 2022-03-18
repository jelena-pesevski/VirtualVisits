import { Component, Input, OnInit } from '@angular/core';
import { ForecastObject } from 'src/app/model/forecast-object.model';
import { ForecastService } from '../services/forecast.service';

@Component({
  selector: 'app-weather-forecast-container',
  templateUrl: './weather-forecast-container.component.html',
  styleUrls: ['./weather-forecast-container.component.css']
})
export class WeatherForecastContainerComponent implements OnInit {

  @Input("country") public country: string ="";

  forecastSource:ForecastObject[]=[];

  constructor(private forecastService:ForecastService) {
    
  }

  ngOnInit(): void {
    this.forecastSource=this.forecastService.getForecast(this.country);
  }

}

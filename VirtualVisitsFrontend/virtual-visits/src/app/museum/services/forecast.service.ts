import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ForecastObject } from 'src/app/model/forecast-object.model';

@Injectable({
  providedIn: 'root'
})
export class ForecastService {

  forecastSource: ForecastObject[]=[];

  private getCitiesUrl:string="https://countriesnow.space/api/v0.1/countries/cities";
  private weatherApiUrlBegin:string="http://api.openweathermap.org/data/2.5/weather?q=";
  private weatherApiUrlEnd:string="&appid=04ac1df18298293deb5cab926f78f2a9&units=metric";

  constructor(private http:HttpClient) { }

  private getCitiesForCountry(country: string): Observable<any> {
    var obj={
      "country": country
    }

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

    return this.http.post<any>(this.getCitiesUrl,JSON.stringify(obj), httpOptions);
  }

  getForecast(country:string) : ForecastObject[]{
    var cities:any[]=[];
    this.forecastSource=[];

    this.getCitiesForCountry(country).subscribe((res:any)=>{
      const shuffled=res.data.sort(()=>{ 
        return Math.random()-0.5; 
      });
      //get forecast forecast for three cities
      cities=shuffled.slice(0,3);
      cities.forEach(c => {
          this.http.get<any>(this.weatherApiUrlBegin+c+this.weatherApiUrlEnd).subscribe({
            next:data=>{
              this.forecastSource.push(new ForecastObject(data.name, data.main.temp, data.weather[0].icon));
            },
            error: err=>{
              console.log("No weather for city:"+c);
            }
          })
      });
    });
    return this.forecastSource;
  }
}

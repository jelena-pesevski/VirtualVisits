import { HttpClient } from '@angular/common/http';
import { fakeAsync, TestBed } from '@angular/core/testing';
import {  of  } from 'rxjs';

import { ForecastService } from './forecast.service';

describe('ForecastService', () => {
  let service: ForecastService;
  let httpClientSpy: jasmine.SpyObj<HttpClient>;

  beforeEach(() => {
    const httpSpy = jasmine.createSpyObj('HttpClient', ['post', 'get']);

    TestBed.configureTestingModule({
      providers: [ForecastService, 
        {
          provide: HttpClient, useValue:httpSpy
        }]
    });
    service = TestBed.inject(ForecastService);
    httpClientSpy=TestBed.inject(HttpClient) as jasmine.SpyObj<HttpClient>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

 /* it('should call forecast for three cities', fakeAsync((done:DoneFn)=>{
    let country:string="France";
    var obj:any={
      "error": false,
      "msg": "cities in France retrieved",
      "data": [
          "Aast",
          "Abancourt",
          "Abbans-Dessus",
          "Abbaretz",
          "Abbecourt"
    ]};
    spyOn<any>(service, 'getCitiesForCountry').and.returnValue(of(obj));
    
    var array=service.getForecast(country);

    expect(array.length).withContext("success for all three cities").toBe(3);
    
    expect(httpClientSpy.get.calls.count())
    .withContext('three calls')
    .toBe(3);
  }));*/
});

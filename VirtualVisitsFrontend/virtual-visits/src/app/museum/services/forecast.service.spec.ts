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
});

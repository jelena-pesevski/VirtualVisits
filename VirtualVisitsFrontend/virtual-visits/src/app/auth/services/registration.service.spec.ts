import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';

import { RegistrationService } from './registration.service';

describe('RegistrationService', () => {
  let service: RegistrationService;
  let httpClientSpy: jasmine.SpyObj<HttpClient>;

  beforeEach(() => {
    const httpSpy = jasmine.createSpyObj('HttpClient', ['post']);


    TestBed.configureTestingModule({  
      providers: [RegistrationService, 
      {
        provide:HttpClient, useValue:httpSpy
      }

    ]});
    service = TestBed.inject(RegistrationService);
    httpClientSpy=TestBed.inject(HttpClient) as jasmine.SpyObj<HttpClient>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

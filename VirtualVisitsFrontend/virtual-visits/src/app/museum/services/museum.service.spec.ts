import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';

import { MuseumService } from './museum.service';

describe('MuseumService', () => {
  let service: MuseumService;
  let httpClientSpy: jasmine.SpyObj<HttpClient>;

  beforeEach(() => {
    const httpSpy = jasmine.createSpyObj('HttpClient', ['get']);

    TestBed.configureTestingModule({
      providers: [MuseumService, 
        {
          provide: HttpClient, useValue:httpSpy
        }]
    });
    service = TestBed.inject(MuseumService);
    httpClientSpy=TestBed.inject(HttpClient) as jasmine.SpyObj<HttpClient>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

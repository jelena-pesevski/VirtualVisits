import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';

import { VirtualVisitService } from './virtual-visit.service';

describe('VirtualVisitService', () => {
  let service: VirtualVisitService;
  let httpClientSpy: jasmine.SpyObj<HttpClient>;

  beforeEach(() => {
    const httpSpy = jasmine.createSpyObj('HttpClient', ['post', 'get']);
    TestBed.configureTestingModule( {providers: [VirtualVisitService, 
      {
        provide: HttpClient, useValue:httpSpy
      }]
    });
    service = TestBed.inject(VirtualVisitService);
    httpClientSpy=TestBed.inject(HttpClient) as jasmine.SpyObj<HttpClient>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

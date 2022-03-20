import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';

import { RefreshTokenService } from './refresh-token.service';

describe('RefreshTokenService', () => {
  let service: RefreshTokenService;
  let httpClientSpy: jasmine.SpyObj<HttpClient>;

  beforeEach(() => {
    const httpSpy = jasmine.createSpyObj('HttpClient', ['post']);

    TestBed.configureTestingModule({
      providers: [RefreshTokenService, 
        {
          provide: HttpClient, useValue:httpSpy
        }]
    });
    service = TestBed.inject(RefreshTokenService);
    httpClientSpy=TestBed.inject(HttpClient) as jasmine.SpyObj<HttpClient>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

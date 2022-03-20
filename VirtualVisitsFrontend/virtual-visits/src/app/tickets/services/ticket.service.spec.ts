import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { LoginService } from 'src/app/auth/services/login.service';

import { TicketService } from './ticket.service';

describe('TicketService', () => {
  let service: TicketService;
  let loginServiceSpy: jasmine.SpyObj<LoginService>;
  let httpClientSpy: jasmine.SpyObj<HttpClient>;

  beforeEach(() => {
    const loginSpy = jasmine.createSpyObj('LoginService', ['getUserId']);
    const httpSpy = jasmine.createSpyObj('HttpClient', ['post']);

    TestBed.configureTestingModule({
      providers: [TicketService, 
        {
          provide: LoginService, useValue:loginSpy
        },
        {
          provide:HttpClient, useValue:httpSpy
        }
  
      ]
    });
    service = TestBed.inject(TicketService);
    loginServiceSpy = TestBed.inject(LoginService) as jasmine.SpyObj<LoginService>;
    httpClientSpy=TestBed.inject(HttpClient) as jasmine.SpyObj<HttpClient>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

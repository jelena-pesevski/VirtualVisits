import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { TokenStorageService } from 'src/app/shared/services/token-storage.service';

import { LoginService } from './login.service';

describe('LoginService', () => {
  let service: LoginService;
  let tokenStorageServiceSpy: jasmine.SpyObj<TokenStorageService>;
  let httpClientSpy: jasmine.SpyObj<HttpClient>;
  
  beforeEach(() => {
    const tokenStorageSpy = jasmine.createSpyObj('TokenStorageService', ['saveToken', 'saveRefreshToken']);
    const httpSpy = jasmine.createSpyObj('HttpClient', ['post']);

    let sessionStore :any = {

    };

    spyOn(window.sessionStorage, 'getItem').and.callFake((key) =>
      key in sessionStore ? sessionStore[key] : null
    );
    spyOn(window.sessionStorage, 'setItem').and.callFake(
      (key, value) => (sessionStore[key] = value + '')
    );
    spyOn(window.sessionStorage, 'clear').and.callFake(() => (sessionStore = {}));

    TestBed.configureTestingModule({
      providers: [LoginService, 
      {
        provide: TokenStorageService, useValue:tokenStorageSpy
      },
      {
        provide:HttpClient, useValue:httpSpy
      }

    ]
    });
    service = TestBed.inject(LoginService);
    tokenStorageServiceSpy = TestBed.inject(TokenStorageService) as jasmine.SpyObj<TokenStorageService>;
    httpClientSpy=TestBed.inject(HttpClient) as jasmine.SpyObj<HttpClient>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });


  describe('manage login', () => {
    it('should store everything in session storage',
      () => {
        var user={
          userId:1 ,
          firstname:"test",
          lastname: "test",
          username: "test",
          mail: "test",
          role: "ADMIN",
          status: "test",
          token: "test",
          refreshToken:"test" ,
          otpToken: "test"
        }

        service.manageLogin(user);
        expect(service.isLoggedIn()).toBe(true);
        expect(service.isAdmin()).toBe(true);
        expect(service.getUserId()).toBe("1");
    });
  });

});

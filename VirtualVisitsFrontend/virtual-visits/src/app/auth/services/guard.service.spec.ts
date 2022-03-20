import { TestBed } from '@angular/core/testing';

import { GuardService } from './guard.service';
import { LoginService } from './login.service';

describe('GuardService', () => {
  let service: GuardService;
  let loginServiceSpy: jasmine.SpyObj<LoginService>;

  beforeEach(() => {
    const loginSpy = jasmine.createSpyObj('LoginService', ['isLoggedIn']);

    TestBed.configureTestingModule({
      providers: [GuardService, 
        {
          provide: LoginService, useValue:loginSpy
        }
      ]
    });
    service = TestBed.inject(GuardService);
    loginServiceSpy = TestBed.inject(LoginService) as jasmine.SpyObj<LoginService>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

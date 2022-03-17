import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  constructor() { }

  logout():void{
    sessionStorage.clear();
  }

  saveToken(token:string):void{
    sessionStorage.removeItem(environment.TOKEN_KEY);
    window.sessionStorage.setItem(environment.TOKEN_KEY, token);
  }

  getToken(): string | null {
    return window.sessionStorage.getItem(environment.TOKEN_KEY);
  }

  saveRefreshToken(token: string): void {
    window.sessionStorage.removeItem(environment.REFRESH_TOKEN_KEY);
    window.sessionStorage.setItem(environment.REFRESH_TOKEN_KEY, token);
  }

  getRefreshToken(): string | null {
    return window.sessionStorage.getItem(environment.REFRESH_TOKEN_KEY);
  }

}

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class RefreshTokenService {

  private serverUrl:string=environment.BASE_URL+"refresh-token";

  constructor(private http:HttpClient, private router:Router, private tokenStorageService:TokenStorageService) { }

  refreshToken(rfrshToken:string){
    //sending http to refresh token url, with refresh token from local storage in body
    //server sends new jwt back if refresh token is valid
      var body={
        refreshToken:rfrshToken
      }
      const httpOptions={
        headers:new HttpHeaders({
          'Content-Type':'application-json'
        })
      };

     return this.http.post<any>(this.serverUrl, JSON.stringify(body), httpOptions);
  }
}

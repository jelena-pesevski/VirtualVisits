import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {  Observable } from 'rxjs';
import { User } from 'src/app/model/user.model';
import { TokenStorageService } from 'src/app/shared/services/token-storage.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {


  constructor(private http:HttpClient, private tokenStorageService:TokenStorageService) { }

  login(uName:string , pass:string): Observable<any>{
    var body={
      username:uName,
      password:pass
    };

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

    //send post to server at /login
    return this.http.post<any>(`${environment.BASE_URL}/auth/login`, JSON.stringify(body), httpOptions);
  }

  manageLogin(user:User){
    this.setLoggedIn();
    this.setIsAdmin(user.role);
    this.setUserId(user.userId);

    if(this.isAdmin() && user.otpToken){
      this.setOtp(user.otpToken);
    }

    //set token in session storage and refreshToken in local 
    this.tokenStorageService.saveToken(user.token);
      
    //set refresh token in local storage
    this.tokenStorageService.saveRefreshToken(user.refreshToken);
  }

  logout(){
    //send post with user id
    this.sendLogout().subscribe(result => { 
      console.log("Logged out "+ result);
    });
    sessionStorage.clear();
  }

  sendLogout():Observable<any>{
    var userId=this.getUserId();
    var httpOptions;
    if(userId){
      httpOptions = {
        headers: new HttpHeaders({
         'userId':userId
        })
      };
     
    }
    return this.http.get(`${environment.BASE_URL}/auth/logout`, httpOptions);
  }

  setLoggedIn(){
    window.sessionStorage.setItem(environment.LOGGED_IN_KEY, "true");
  }

  isLoggedIn(){
    return window.sessionStorage.getItem(environment.LOGGED_IN_KEY)!=null;
  }

  setIsAdmin(role:string){
    if(role=="ADMIN"){
      window.sessionStorage.setItem(environment.IS_ADMIN_KEY, "true");
    }
  }

  isAdmin(){
    return window.sessionStorage.getItem(environment.IS_ADMIN_KEY)!=null;
  }

  setUserId(id:number){
    window.sessionStorage.setItem(environment.ID_KEY, id.toString());
  }

  getUserId(){
    return window.sessionStorage.getItem(environment.ID_KEY);
  }

  setOtp(otpToken:string){
    window.sessionStorage.setItem(environment.OTP_TOKEN_KEY, otpToken);
  }

  getOtp(){
    return window.sessionStorage.getItem(environment.OTP_TOKEN_KEY);
  }
}

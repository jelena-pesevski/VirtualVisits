import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, throwError, map, BehaviorSubject } from 'rxjs';
import { User } from 'src/app/model/user.model';
import { TokenStorageService } from 'src/app/shared/services/token-storage.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public loggedIn: boolean=false;
  public isLogged: BehaviorSubject<boolean>=new BehaviorSubject<boolean>(false);
  public activeUser: User | null=null;
  private serverUrl: string=environment.BASE_URL+"login";

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
    return this.http.post<any>(this.serverUrl, JSON.stringify(body), httpOptions);
  }

  setTokens(user:User){
    this.activeUser=user;
    this.loggedIn=true;
    this.isLogged.next(true);

    //set token in session storage and refreshToken in local 
    this.tokenStorageService.saveToken(this.activeUser.token);
      
    //set refresh token in local storage
    this.tokenStorageService.saveRefreshToken(this.activeUser.refreshToken);
  }

  logout(){
    this.activeUser=null;
    this.loggedIn=false;
    this.isLogged.next(false);

    //this logout deletes tokens
    this.tokenStorageService.logout();
  }

}

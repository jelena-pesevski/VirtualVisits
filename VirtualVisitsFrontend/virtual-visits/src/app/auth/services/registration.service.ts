import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private serverUrl: string=environment.BASE_URL+"sign-up";

  constructor(private http:HttpClient) { }

  register(body:string) :Observable<any>{ 
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };
    return this.http.post<any>(this.serverUrl, body, httpOptions);
  }
}

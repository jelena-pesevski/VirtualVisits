import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {


  constructor(private http:HttpClient) { }

  register(body:string) :Observable<any>{ 
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };
    return this.http.post<any>(`${environment.BASE_URL}/auth/sign-up`, body, httpOptions);
  }
}

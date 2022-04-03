import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Log } from 'src/app/model/log.model';
import { Museum } from 'src/app/model/museum.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LogService {

  constructor(private http:HttpClient) { }

  public getLogs():Observable<Log[]>{
    return this.http.get<Log[]>(`${environment.BASE_URL}/logs`);
  }

  
  downloadPdf(): any {
		return this.http.get(`${environment.BASE_URL}/logs/pdf`, {responseType: 'blob'});
  }
}

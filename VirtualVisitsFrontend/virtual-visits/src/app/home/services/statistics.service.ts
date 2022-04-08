import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Statistics } from 'src/app/model/statistics.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StatisticsService {

  constructor(private http:HttpClient) { }

  getStatistics():Observable<any>{
    return this.http.get<any>(`${environment.BASE_URL}/users/statistics`);
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Museum } from 'src/app/model/museum.model';
import { SingleMuseum } from 'src/app/model/single-museum.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MuseumService {

  private serverUrl=environment.BASE_URL+"museums";

  constructor(private http:HttpClient) { }

  public getMuseums():Observable<Museum[]>{
    return this.http.get<Museum[]>(this.serverUrl);
  }

  public getSingleMuseum(id:number):Observable<SingleMuseum>{
    return this.http.get<SingleMuseum>(this.serverUrl+"/"+id);
  }
}

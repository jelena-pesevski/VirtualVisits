import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Museum } from 'src/app/model/museum.model';
import { MuseumDetails } from 'src/app/model/single-museum.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MuseumService {

  private battutaKey : string="b2fc4071c4cd80fc33f10ec7f21d13f1";
 
  constructor(private http:HttpClient) { }

  getMuseums():Observable<Museum[]>{
    return this.http.get<Museum[]>(`${environment.BASE_URL}/museums`);
  }

  getMuseumDetails(id:number):Observable<MuseumDetails>{
    return this.http.get<MuseumDetails>(`${environment.BASE_URL}/museums/${id}`);
  }

  getMuseumsForAdmin(): Observable<MuseumDetails[]>{
    return this.http.get<MuseumDetails[]>(`${environment.BASE_URL}/museums/detailed`);
  }

  insertMuseum(museum:MuseumDetails):Observable<MuseumDetails>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

    return this.http.post<MuseumDetails>(`${environment.BASE_URL}/museums`, JSON.stringify(museum), httpOptions);
  }

  updateMuseum(museum:MuseumDetails):Observable<MuseumDetails>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

    return this.http.put<MuseumDetails>(`${environment.BASE_URL}/museums/${museum.museumId}`, JSON.stringify(museum), httpOptions);
  }

  deleteMuseum(museumId:number) : Observable<any>{
    return this.http.delete<any>(`${environment.BASE_URL}/museums/${museumId}`);
  }

  getCountries(){
    return this.http.get("https://restcountries.com/v3.1/region/europe");
  }

  getRegionsForCountry(cca2:string){
    return this.http.jsonp(`http://battuta.medunes.net/api/region/${cca2}/all/?key=${this.battutaKey}`, 'callback');
  }

  getCitiesForRegion(region:any){
    return this.http.jsonp(`http://battuta.medunes.net/api/city/${region.country}/search/?region=${region.region}&key=${this.battutaKey}`, 'callback');
  }

  getCCA2ForCountryName(name:string){
    return this.http.get(`https://restcountries.com/v3.1/name/${name}`);
  }


}

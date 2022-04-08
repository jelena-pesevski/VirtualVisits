import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ActiveVisit } from 'src/app/model/active-visit.model';
import { VirtualVisit } from 'src/app/model/virtual-visit.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VirtualVisitService {

  private getUpcomingVisitsForMuseumUrl=environment.BASE_URL+"virtual-visits/upcoming/museum";
  private getActiveVisitsUrl=environment.BASE_URL+"virtual-visits/active";
  private attendVisitUrl=environment.BASE_URL+"virtual-visits/attend";

  //current visit that user is attending
  private currAttendingVisit:ActiveVisit | null=null;

  constructor(private http:HttpClient) { }

  getUpcomingVisitsForMuseum(museumId:number):Observable<VirtualVisit[]>{
    return this.http.get<VirtualVisit[]>(`${environment.BASE_URL}/virtual-visits/upcoming/museum?id=${museumId}`);
  }

  getActiveVisits():Observable<VirtualVisit[]>{
    return this.http.get<VirtualVisit[]>(`${environment.BASE_URL}/virtual-visits/active`);
  }

  getUpcoming():Observable<VirtualVisit[]>{
    return this.http.get<VirtualVisit[]>(`${environment.BASE_URL}/virtual-visits/upcoming`);
  }

  attendVisit(obj:any):Observable<ActiveVisit>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

    return this.http.post<ActiveVisit>(`${environment.BASE_URL}/virtual-visits/attend`, JSON.stringify(obj), httpOptions);
  }

  getCurrAttendingVisit(): ActiveVisit | null{
    return this.currAttendingVisit;
  }

  setCurrAttendingVisit(visit:ActiveVisit){
    this.currAttendingVisit=visit;
  }

  deleteVirtualVisit(visitId:number) : Observable<any>{
    return this.http.delete<any>(`${environment.BASE_URL}/virtual-visits/${visitId}`);
  }

  insertVirtualVisit(formData:FormData):Observable<VirtualVisit>{
    return this.http.post<VirtualVisit>(`${environment.BASE_URL}/virtual-visits`, formData);
  }

  updateVirtualVisit(id:any, formData:FormData):Observable<VirtualVisit>{
    return this.http.put<VirtualVisit>(`${environment.BASE_URL}/virtual-visits/${id}`, formData);
  }
}

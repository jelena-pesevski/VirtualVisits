import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginService } from 'src/app/auth/services/login.service';
import { VirtualVisit } from 'src/app/model/virtual-visit.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private getTicketServerUrl=environment.BASE_URL+"ticket";
  private virtualVisit:VirtualVisit | null=null;

  constructor(private http:HttpClient, private loginService:LoginService) { }

  getTicket(obj:any):Observable<any>{
    obj.cashAmount=this.virtualVisit?.price;
    obj.userId=this.loginService.getUserId();
    obj.virtualVisitId=this.virtualVisit?.virtualVisitId;

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };
    console.log(JSON.stringify(obj));

    return this.http.post<any>(this.getTicketServerUrl, JSON.stringify(obj), httpOptions);
  }

  setVirtualVisit(visit:VirtualVisit){
    this.virtualVisit=visit;
  }

  getVirtualVisit(): VirtualVisit | null{
    return this.virtualVisit;
  }

  parseDate(date:string){
    const array=date.split("/");
    var year=2000;
    year+=parseInt(array[1]);

    var month=array[0];

    return year+"-"+month+"-01";
  }
}

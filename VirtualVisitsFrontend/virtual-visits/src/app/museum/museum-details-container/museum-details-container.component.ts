import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Museum } from 'src/app/model/museum.model';
import { SingleMuseum } from 'src/app/model/single-museum.model';
import { VirtualVisit } from 'src/app/model/virtual-visit.model';
import { TicketService } from 'src/app/tickets/services/ticket.service';
import { VirtualVisitService } from 'src/app/virtual-visit/services/virtual-visit.service';
import { MapContainerComponent } from '../map-container/map-container.component';
import { MuseumService } from '../services/museum.service';

@Component({
  selector: 'app-museum-details-container',
  templateUrl: './museum-details-container.component.html',
  styleUrls: ['./museum-details-container.component.css']
})
export class MuseumDetailsContainerComponent implements OnInit {

  museum:SingleMuseum | null=null;
  displayedColumns: string[] = [ 'date', 'start', 'duration', 'price','action'];
  dataSource : VirtualVisit[] =[];

  constructor(private activatedRoute: ActivatedRoute, private visitsService:VirtualVisitService,
     private museumService:MuseumService, private router:Router,private dialog: MatDialog, private ticketService:TicketService) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(param => {  
      // tslint:disable-next-line: no-string-literal  
        if(param!=null){
          this.showMuseumData(param['museumId']);
          this.loadVisits(param['museumId']);
        }else{
          this.router.navigate(['home/museums']);
        }
      });
  }

  showMuseumData(id:any){
    if(id==null)return;

    this.museumService.getSingleMuseum(id).subscribe({
      next:data=>{
        this.museum=data;
      },
      error: err=>{
        console.log(err);
        if(err.status==401){
          this.router.navigate(['']);
        }else{
          this.router.navigate(['home/museums']);
        }
      }
    })
  }

  loadVisits(id:any){
    if(id==null)return;

    this.visitsService.getUpcomingVisitsForMuseum(id).subscribe({
      next:data=>{
        this.dataSource=data;
      },
      error: err => {
        console.log(err);
        if(err.status==401){
          this.router.navigate(['']);
        }
      }
    });
  }

  buyTicket(element:VirtualVisit){
    this.ticketService.setVirtualVisit(element);
    this.router.navigate(['/buy-ticket']);
  }

  seeOnAMap(){
    this.dialog.open(MapContainerComponent, {
      width: '550px',
      data: {
        longitude: this.museum?.longitude,
        latitude: this.museum?.latitude
      }
    });
  }
}

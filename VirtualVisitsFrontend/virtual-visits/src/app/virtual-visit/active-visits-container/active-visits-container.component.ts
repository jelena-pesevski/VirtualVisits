import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { VirtualVisit } from 'src/app/model/virtual-visit.model';
import { TicketService } from 'src/app/tickets/services/ticket.service';
import { VirtualVisitService } from '../services/virtual-visit.service';
import { VisitEntryComponent } from '../visit-entry/visit-entry.component';

@Component({
  selector: 'app-active-visits-container',
  templateUrl: './active-visits-container.component.html',
  styleUrls: ['./active-visits-container.component.css']
})
export class ActiveVisitsContainerComponent implements OnInit {

  displayedColumnsActiveVisits: string[] = [ 'museumName', 'date', 'start', 'duration','action'];
  displayedColumnsUpcomingVisits: string[] = [ 'museumName', 'date', 'start', 'duration','action'];
  activeVisits : VirtualVisit[] =[];
  upcomingVisits: VirtualVisit[]=[];

  constructor(private visitsService:VirtualVisitService, private router:Router, private dialog: MatDialog, private ticketService:TicketService) { }

  ngOnInit(): void {
    this.loadActiveVisits();
    this.loadUpcomingVisits();
  }

  loadActiveVisits(){
    this.visitsService.getActiveVisits().subscribe({
      next:data=>{
        this.activeVisits=data;
      },
      error: err => {
        console.log(err);
        if(err.status==401){
          this.router.navigate(['']);
        }
      }
    });
  }

  loadUpcomingVisits(){
    this.visitsService.getUpcoming().subscribe({
      next:data=>{
        this.upcomingVisits=data;
      },
      error: err => {
        console.log(err);
        if(err.status==401){
          this.router.navigate(['']);
        }
      }
    });
  }

  attend(element:VirtualVisit){
    this.dialog.open(VisitEntryComponent, {
      width: '550px',
      data: {
        virtualVisitId:element.virtualVisitId
      }
    });
  }

  buyTicket(element:VirtualVisit){
    this.ticketService.setVirtualVisit(element);
    this.router.navigate(['/buy-ticket']);
  }
}

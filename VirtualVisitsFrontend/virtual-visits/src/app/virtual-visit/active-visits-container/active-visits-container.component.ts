import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { VirtualVisit } from 'src/app/model/virtual-visit.model';
import { VirtualVisitService } from '../services/virtual-visit.service';
import { VisitEntryComponent } from '../visit-entry/visit-entry.component';

@Component({
  selector: 'app-active-visits-container',
  templateUrl: './active-visits-container.component.html',
  styleUrls: ['./active-visits-container.component.css']
})
export class ActiveVisitsContainerComponent implements OnInit {

  displayedColumns: string[] = [ 'museumName', 'date', 'start', 'duration','action'];
  dataSource : VirtualVisit[] =[];

  constructor(private visitsService:VirtualVisitService, private router:Router, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.loadVisits();
  }

  loadVisits(){
    this.visitsService.getActiveVisits().subscribe({
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

  attend(element:VirtualVisit){
    this.dialog.open(VisitEntryComponent, {
      width: '550px',
      data: {
        virtualVisitId:element.virtualVisitId
      }
    });
  }
}

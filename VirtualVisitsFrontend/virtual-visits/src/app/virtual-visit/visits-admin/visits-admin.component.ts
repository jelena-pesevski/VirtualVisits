import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ConfirmModalComponent } from 'src/app/confirm-modal/confirm-modal.component';
import { VirtualVisit } from 'src/app/model/virtual-visit.model';
import { VirtualVisitService } from '../services/virtual-visit.service';
import { VisitsEditComponent } from '../visits-edit/visits-edit.component';

@Component({
  selector: 'app-visits-admin',
  templateUrl: './visits-admin.component.html',
  styleUrls: ['./visits-admin.component.css']
})
export class VisitsAdminComponent implements OnInit {

  displayedColumns: string[] = ['virtualVisitId', 'museumName', 'date', 'start', 'duration', 'price', 'edit','delete'];
  dataSource = new MatTableDataSource<VirtualVisit>();

  constructor(private visitsService:VirtualVisitService, private dialog: MatDialog,  private snackBar:MatSnackBar
    ,private router:Router) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData(){
    this.visitsService.getUpcoming().subscribe({
      next: data => {
        this.dataSource=new MatTableDataSource(data);
      },
      error: err => {
        console.log(err);
        if(err.status==401){
          this.router.navigate(['']);
        }
      }
    })
  }

  delete(element:VirtualVisit){
    this.dialog.open(ConfirmModalComponent, {
      width: '300px'
    })
    .afterClosed()
    .subscribe(result => {
      if (result && element.virtualVisitId) {
        this.visitsService.deleteMuseum(element.virtualVisitId).subscribe({
          next:data=>{
            this.snackBar.open("Virtual visit is deleted!", undefined, {
              duration: 2000
            });
            this.loadData();
          },
          error:err=>{
            this.snackBar.open("Error, virtual visit can't be deleted", undefined, {
              duration: 2000
            });

            if(err.status==401){
              this.router.navigate(['']);
            }
          }
        });
       
      }
    });
  }

  edit(element:VirtualVisit){
   this.dialog.open(VisitsEditComponent, {
      width: '600px',
      data: {
        virtualVisit: element,
        isEdit: true
      }
    })
    .afterClosed()
    .subscribe(result => {
      this.loadData();
    });
  }

  add(){

  }
}

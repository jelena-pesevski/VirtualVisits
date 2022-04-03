import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import {  Router } from '@angular/router';
import { ConfirmModalComponent } from 'src/app/confirm-modal/confirm-modal.component';
import { MuseumDetails } from 'src/app/model/single-museum.model';
import { MapContainerComponent } from '../map-container/map-container.component';
import { MuseumEditComponent } from '../museum-edit/museum-edit.component';
import { MuseumService } from '../services/museum.service';

@Component({
  selector: 'app-museums-admin',
  templateUrl: './museums-admin.component.html',
  styleUrls: ['./museums-admin.component.css']
})
export class MuseumsAdminComponent implements OnInit {

  dataSource:MatTableDataSource<MuseumDetails>;
  museums: MuseumDetails[] = [];
  public displayedColumns: string[] = ['museumId', 'name', 'city', 'country', 'address', 'phone', 'type', 'geolocation', 'edit', 'delete'];

  constructor(private museumService:MuseumService, private dialog: MatDialog, private router:Router,  private snackBar:MatSnackBar) {
    this.dataSource=new MatTableDataSource(this.museums);
  }

  ngOnInit(): void {
    this.loadData();
  }

  loadData(){
    this.museumService.getMuseumsForAdmin().subscribe({
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

 
  seeOnAMap(element:MuseumDetails){
    this.dialog.open(MapContainerComponent, {
      width: '550px',
      data: {
        longitude: element.longitude,
        latitude: element.latitude
      }
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  delete(element:MuseumDetails){
    this.dialog.open(ConfirmModalComponent, {
      width: '300px'
    })
    .afterClosed()
    .subscribe(result => {
      if (result && element.museumId) {
        this.museumService.deleteMuseum(element.museumId).subscribe({
          next:data=>{
            this.snackBar.open("Museum is deleted!", undefined, {
              duration: 2000
            });
            this.loadData();
          },
          error:err=>{
            this.snackBar.open("Error, museum can't be deleted", undefined, {
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

  edit(element:MuseumDetails){
    this.dialog.open(MuseumEditComponent, {
      width: '600px',
      data: {
        museum: element,
        isEdit: true
      }
    })
    .afterClosed()
    .subscribe(result => {
      this.loadData();
    });
  }

  add(){
    this.dialog.open(MuseumEditComponent, {
      width: '600px',
      data: {
        isEdit: false
      }
    })
    .afterClosed()
    .subscribe(result => {
      this.loadData();
    });
  }
  
}

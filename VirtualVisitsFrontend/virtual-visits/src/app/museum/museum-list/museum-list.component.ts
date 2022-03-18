import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Museum } from 'src/app/model/museum.model';
import { MuseumService } from '../services/museum.service';

@Component({
  selector: 'app-museum-list',
  templateUrl: './museum-list.component.html',
  styleUrls: ['./museum-list.component.css']
})
export class MuseumListComponent implements OnInit {

  dataSource:MatTableDataSource<Museum>;
  museums: Museum[] = [];
  public displayedColumns: string[] = ['museumId', 'name', 'city', 'details'];

  constructor(private service:MuseumService, private router:Router) {
    this.dataSource=new MatTableDataSource(this.museums);
  }

  ngOnInit(): void {
    this.load();
  }

  public load(){
    this.service.getMuseums().subscribe({
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

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  details(element:Museum){
    this.router.navigate(['home/museums/details', element.museumId]);
  }
}

import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Museum } from 'src/app/model/museum.model';
import { SingleMuseum } from 'src/app/model/single-museum.model';
import { MuseumService } from '../services/museum.service';

@Component({
  selector: 'app-museum-container',
  templateUrl: './museum-container.component.html',
  styleUrls: ['./museum-container.component.css']
})
export class MuseumContainerComponent implements OnInit {

  constructor(){

  }

  ngOnInit(): void {
    
  }
}

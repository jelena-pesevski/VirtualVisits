import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Log } from 'src/app/model/log.model';
import { LogService } from '../services/log.service';
import * as fileSaver from 'file-saver';
import { saveAs } from 'file-saver';
 

@Component({
  selector: 'app-logs-container',
  templateUrl: './logs-container.component.html',
  styleUrls: ['./logs-container.component.css']
})
export class LogsContainerComponent implements OnInit {

  dataSource:MatTableDataSource<Log>;
  displayedColumns: string[] = [ 'action','info', 'dateTime'];
  logs : Log[] =[];

  constructor(private router:Router, private logService:LogService) { 
    this.dataSource=new MatTableDataSource(this.logs);
  }

  ngOnInit(): void {
    this.loadLogs();
    
  }

  loadLogs(){
    this.logService.getLogs().subscribe({
      next:data=>{
        this.dataSource=new MatTableDataSource(data);
      },
      error: err => {
        console.log(err);
        if(err.status==401){
          this.router.navigate(['']);
        }
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


  downloadPdf(){
    this.logService.downloadPdf().subscribe((response: any) => {
			let blob:any = new Blob([response], { type: "application/pdf" });
			const url = window.URL.createObjectURL(blob);
			//window.open(url);
			saveAs(blob, 'logs.pdf');
			}), (error: any) => console.log('Error downloading the file'),
			() => console.info('File downloaded successfully');
  }
}

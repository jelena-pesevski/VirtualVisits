import { AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { interval, startWith, Subscription, switchMap } from 'rxjs';
import { Statistics } from 'src/app/model/statistics.model';
import { StatisticsService } from '../services/statistics.service';
import {CategoryScale, Chart, LinearScale, LineController, LineElement, PointElement, Title} from "chart.js";

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements AfterViewInit {

  @ViewChild('chartCanvas') private chartCanvas!:ElementRef;

  timeInterval!: Subscription;
  chart: any;
  numOfLoggedIn:number=0;
  numOfRegistrated:number=0;
  pairs:any;

  constructor(private service:StatisticsService) { 
    Chart.register(LineController, LineElement, PointElement, LinearScale, Title, CategoryScale);
  }

  ngAfterViewInit(): void {
    this.service.getStatistics().subscribe({
      next:data=>{
        this.pairs=data.pairs;
        this.numOfLoggedIn=data.numOfLoggedIn;
        this.numOfRegistrated=data.numOfRegistrated;
        this.chart=new Chart(this.chartCanvas.nativeElement, {
          type: 'line',
          data: {
              labels: this.pairs.map((p:any)=>{
                if(p.hour<0){
                  return p.hour+24;
                }else{
                  return p.hour;
                }
              }),
              datasets: [{
                  label: '# of active users in last 24 hours',
                  data: this.pairs.map((p:any)=>p.numOfUsers),
                  backgroundColor:"blue",
                  borderColor: "lightblue",
                  borderWidth: 1
              }]
          },
          options: {
              scales: {
                  y: {
                      beginAtZero: true,
                      ticks:{
                        stepSize: 1
                      }
                  }
              }
          }
        });
      }
    })



    
  }
}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/auth/services/login.service';
import { VirtualVisitService } from '../services/virtual-visit.service';

@Component({
  selector: 'app-active-visit-container',
  templateUrl: './active-visit-container.component.html',
  styleUrls: ['./active-visit-container.component.css']
})
export class ActiveVisitContainerComponent implements OnInit {

  imagesUrls:string[] | null =[];
  videoUrl:string | null =null;
  ytLink: string | null=null;

  constructor(private loginService:LoginService, private virtualVisitService:VirtualVisitService, private router:Router) {
    var visitContent=virtualVisitService.getCurrAttendingVisit();
    if(visitContent){
      this.imagesUrls=visitContent.imagesUrls;
      this.videoUrl=visitContent.videoUrl;
      this.ytLink=visitContent.ytLink;


      var delay=visitContent.endingTimeInMillis- Date.now();

      setTimeout(function(){
        loginService.logout();
        router.navigate(['']);
      }, delay);
    }
  }

  ngOnInit(): void {
  }

}

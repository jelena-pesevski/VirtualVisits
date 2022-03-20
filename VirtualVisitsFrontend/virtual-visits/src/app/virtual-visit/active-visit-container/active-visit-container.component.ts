import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/auth/services/login.service';
import { VirtualVisitService } from '../services/virtual-visit.service';
import {DomSanitizer,SafeResourceUrl,} from '@angular/platform-browser';

@Component({
  selector: 'app-active-visit-container',
  templateUrl: './active-visit-container.component.html',
  styleUrls: ['./active-visit-container.component.css']
})
export class ActiveVisitContainerComponent implements OnInit {

  imagesUrls:string[] | null =[];
  videoUrl:string | null =null;
  ytLink: any | null=null;

  constructor(private loginService:LoginService, private virtualVisitService:VirtualVisitService, private router:Router, public sanitizer:DomSanitizer) {
    var visitContent=virtualVisitService.getCurrAttendingVisit();
    if(visitContent){
      this.imagesUrls=visitContent.imagesUrls;
      this.videoUrl=visitContent.videoUrl;
      if(visitContent.ytLink){
        this.ytLink=this.sanitizer.bypassSecurityTrustResourceUrl(visitContent.ytLink);
      }
      

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

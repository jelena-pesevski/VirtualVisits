import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/auth/services/login.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  isAdmin:boolean=false;

  constructor(private router:Router, private loginService:LoginService) { 
    if(loginService.isAdmin()){
      this.isAdmin=true;
    }
  }

  ngOnInit(): void {
  }

   logout(){
    this.loginService.logout();

    //navigating to login page
    this.router.navigate(['']);
  }

  goToJSPApp(){
    window.open(`http://localhost:8080/VirtualVisitsAdminApp/home.jsp?otp=${this.loginService.getOtp()}`, "_blank");
  }
}

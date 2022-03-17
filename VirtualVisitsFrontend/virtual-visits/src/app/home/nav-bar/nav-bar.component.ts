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
    if(loginService?.activeUser?.role==="ADMIN"){
      this.isAdmin=true;
    }
  }

  ngOnInit(): void {
  }

  public logout(){
    this.loginService.logout();

    //navigating to login page
    this.router.navigate(['']);
  }
}

import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { map, Observable } from 'rxjs';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class GuardService implements CanActivate {

  constructor(private loginService:LoginService, private router:Router) { }

  canActivate(): boolean | Observable<boolean> {
    /*if (this.loginService.isLogged.) {
      console.log("ulogovan je");
      return true;
    } else {
      console.log("nije ulogovan");
      this.router.navigate(['']);
      return false;
    }*/
    return this.loginService.isLogged.pipe(map(logged => {
      if(logged) {
        console.log("ulogovan je");
        return true;
      }
      console.log("nije ulogovan ");
      this.router.navigate(['']);
      return false;
    })
    )
  }
 
}

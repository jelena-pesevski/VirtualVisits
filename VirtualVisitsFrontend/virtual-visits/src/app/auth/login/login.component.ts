import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user.model';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public form: FormGroup=new FormGroup({});
  public error:string|null=null;

  constructor(private formBuilder:FormBuilder, private loginService:LoginService, private router:Router) { }

  ngOnInit(): void {
    this.form=this.formBuilder.group({
      username:[null, Validators.required],
      password:[null, Validators.required]
    })
  }

  public login(form: any){
    this.loginService.login(form.value.username, form.value.password).subscribe({
      next: data => {
        console.log(data);
        var u=new User(data.username, data.userId, data.firstname, data.lastname, data.mail, data.role,
          data.status, data.token, data.refreshToken, data.otpToken);
        this.loginService.setTokens(u);
        this.router.navigate(['home']);
      },
      error: err => {
        console.log(err);
        this.error="Invalid username or password";
      }
    });
  }

}

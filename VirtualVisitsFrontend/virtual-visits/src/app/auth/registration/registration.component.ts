import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, ValidatorFn, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { checkPasswordsValidator } from 'src/app/shared/check-password.directive';
import { RegistrationService } from '../services/registration.service';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const invalidCtrl = !!(control && control.invalid && control.parent && control.parent.dirty);
    const invalidParent = !!(control && control.parent && control.parent.invalid && control.parent.dirty);

    return (invalidCtrl || invalidParent);
  }
}

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  public form: FormGroup=new FormGroup({});
  public error:string|null=null;

  matcher = new MyErrorStateMatcher();

  constructor(private formBuilder:FormBuilder, private registrationService:RegistrationService, private router:Router, private snackBar:MatSnackBar) { }

  ngOnInit(): void {
    this.form=this.formBuilder.group({
      firstname:[null, Validators.required],
      lastname:[null, Validators.required],
      mail:[null, [Validators.required, Validators.email]],
      username:[null, [Validators.required, Validators.minLength(12), Validators.pattern('^[^@#\/]+$')]],
      password:[null, [Validators.required, Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{15,}$/)] ],
      confirmPassword:[null, Validators.required]
    }, { validator: checkPasswordsValidator })
  }

  register(form:any){
    var signUpRequest={
      firstname:form.value.firstname,
      lastname:form.value.lastname,
      mail:form.value.mail,
      username:form.value.username,
      password:form.value.password
    }

    this.registrationService.register(JSON.stringify(signUpRequest)).subscribe({
      next:data=>{
        this.snackBar.open("Registration is requested!", undefined, {
          duration: 2000
        });
        this.router.navigate(['']);
      },
      error: err=>{
        console.log(err);
        this.error="Sign up failed";
      }
    })
  }
 
}

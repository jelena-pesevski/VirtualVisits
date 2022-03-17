import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { TicketService } from '../services/ticket.service';

@Component({
  selector: 'app-buy-ticket',
  templateUrl: './buy-ticket.component.html',
  styleUrls: ['./buy-ticket.component.css']
})
export class BuyTicketComponent implements OnInit {

  public form: FormGroup=new FormGroup({});

  constructor(private ticketService:TicketService, private formBuilder:FormBuilder, private router:Router, private snackBar:MatSnackBar) { }

  ngOnInit(): void {
    this.form=this.formBuilder.group({
      firstname:[null, Validators.required],
      lastname:[null, Validators.required],
      creditCardNumber:[null, Validators.required],
      creditCardType:[null, Validators.required],
      expirationDate:[null, [Validators.required, Validators.pattern('^[0-9]{2}/[0-9]{2}$')]],
      pin:[null, Validators.required],
    })
  }

  buyTicket(form:any){
    var expDate=this.ticketService.parseDate(form.value.expirationDate);
    var obj={
      firstname:form.value.firstname,
      lastname:form.value.lastname,
      creditCardNumber:form.value.creditCardNumber,
      creditCardType:form.value.creditCardType,
      pin:form.value.pin,
      expirationDate:expDate
    };

    this.ticketService.getTicket(obj).subscribe({
      next:data=>{
            this.snackBar.open("Ticket bought, you should recieve an email", undefined, {
              duration: 3000
            });
            this.router.navigate(['home/museums']);
      },
      error:err=>{
       this.error();
      }
    })
  }

  error(){
    console.log("error transaction process");
    this.snackBar.open("Buying ticket is not possible", undefined, {
      duration: 2000
    });
    this.router.navigate(['home/museums']);
  }

}

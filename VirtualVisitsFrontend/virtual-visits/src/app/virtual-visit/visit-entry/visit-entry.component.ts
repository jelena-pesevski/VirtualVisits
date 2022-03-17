import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { VirtualVisitService } from '../services/virtual-visit.service';

@Component({
  selector: 'app-visit-entry',
  templateUrl: './visit-entry.component.html',
  styleUrls: ['./visit-entry.component.css']
})
export class VisitEntryComponent implements OnInit {

  public form: FormGroup=new FormGroup({});
  virtualVisitId:number;

  constructor(private formBuilder:FormBuilder, private virtualVisitService:VirtualVisitService, private router:Router, private snackBar:MatSnackBar,private dialogRef: MatDialogRef<VisitEntryComponent>,  @Inject(MAT_DIALOG_DATA) data: any) { 
    this.virtualVisitId=data.virtualVisitId;
  }

  ngOnInit(): void {
    this.form=this.formBuilder.group({
      ticketNumber:[null, Validators.required],
    })
  }

  public attend(form: any){
    var obj={
      ticketNumber:form.value.ticketNumber,
      virtualVisitId:this.virtualVisitId
    }

    this.virtualVisitService.attendVisit(obj).subscribe({
      next:data=>{
        this.virtualVisitService.setCurrAttendingVisit(data);
        this.dialogRef.close();
        this.router.navigate(['virtual-visits/attend']);
      },
      error:err=>{
        console.log(err);
        this.snackBar.open("Ticket number is not valid", undefined, {
          duration: 2000
        });
        this.router.navigate(['home/virtual-visits']);
      }
    })
  }

  close() {
    this.dialogRef.close();
  }
}

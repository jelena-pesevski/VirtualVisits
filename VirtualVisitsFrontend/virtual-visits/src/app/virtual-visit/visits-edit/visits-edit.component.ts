import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Museum } from 'src/app/model/museum.model';
import { VirtualVisit } from 'src/app/model/virtual-visit.model';
import { MuseumService } from 'src/app/museum/services/museum.service';
import { VirtualVisitService } from '../services/virtual-visit.service';

@Component({
  selector: 'app-visits-edit',
  templateUrl: './visits-edit.component.html',
  styleUrls: ['./visits-edit.component.css']
})
export class VisitsEditComponent implements OnInit {

  public form: FormGroup = new FormGroup({});
  public museums:Museum[]=[];
  public virtualVisit: VirtualVisit=new VirtualVisit();
  public isEdit = false; 

  constructor(private visitsService:VirtualVisitService, private museumService:MuseumService, public formBuilder: FormBuilder,  private snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<VisitsEditComponent>,
    @Inject(MAT_DIALOG_DATA) data: any, private dialog: MatDialog, private router:Router) {
    this.loadMuseums();
    this.isEdit=data.isEdit;
    if(data.isEdit){
      this.virtualVisit=data.virtualVisit;
    }
  }

  ngOnInit(): void {
    console.log(this.virtualVisit.museumId);
    this.form = this.formBuilder.group({
      museumId: [this.virtualVisit.museumId, Validators.required]
    });
  }


  loadMuseums(){
    this.museumService.getMuseums().subscribe({
      next:data=>{
        console.log(data);
        this.museums=data;
      },
      error:err=>{
        console.log(err);
        if(err.status==401){
          this.router.navigate(['']);
        }
      }
    })
  }

  close() {
    this.dialogRef.close();
  }

  save({ value, valid }: { value: VirtualVisit, valid: boolean }){

  }
}

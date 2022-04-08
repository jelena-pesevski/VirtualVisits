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
  images:string [] = [];
  video:string | null= null;

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
    this.form = this.formBuilder.group({
      museumId: [this.virtualVisit.museumId, Validators.required],
      date: [this.virtualVisit.date, Validators.required],
      start:[this.virtualVisit.start, Validators.required],
      duration:[this.virtualVisit.duration, Validators.required],
      price:[this.virtualVisit.price, Validators.required],
      ytLink:[this.virtualVisit.ytLink]
    });
  }


  loadMuseums(){
    this.museumService.getMuseums().subscribe({
      next:data=>{
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
    if(valid){
      if(value.ytLink?.length==0){
        value.ytLink=null;
      }

      var formData = new FormData();

      formData.append("virtualVisit", JSON.stringify(value));
     
      for(let i=0; i<this.images.length; i++){
        formData.append("image", this.images[i]);
      }
    
      if(this.video!=null && value.ytLink==null){
        formData.append("video", this.video);
      }

      if(this.isEdit) {
        value.virtualVisitId=this.virtualVisit.virtualVisitId;
        this.visitsService.updateVirtualVisit(value.virtualVisitId, formData).subscribe({
          next:data=>{
            console.log("updated");
            this.snackBar.open("Virtual visit is saved", undefined, { 
              duration: 2000,
            });
            this.form.reset();
            this.dialogRef.close();
          },
          error:err=>{
            console.log(err);
            this.snackBar.open("Virtual visit can't be saved", undefined, { 
              duration: 2000,
            });
            if(err.status==401){
              this.router.navigate(['']);
            }
          }
        })
      } else {
        this.visitsService.insertVirtualVisit(formData).subscribe({
          next:data=>{
            console.log("inserted");
            this.snackBar.open("Virtual visit is saved", undefined, { 
              duration: 2000,
            });
            this.form.reset();
            this.dialogRef.close();
          },
          error:err=>{
            console.log(err);
            this.snackBar.open("Virtual visit can't be saved", undefined, { 
              duration: 2000,
            });
            if(err.status==401){
              this.router.navigate(['']);
            }
          }
        })
      }

    }
   
  }

  onImagesUpload(event:any){
 /*   if(event.target.files.length<5 || event.target.files.length>10){
      this.snackBar.open("You must upload 5-10 images!", undefined, {
        duration: 2000
      });
      return;
    }*/

    this.images=[];
    for (var i = 0; i < event.target.files.length; i++) { 
      this.images.push(event.target.files[i]);
    }
  }

  onVideoUpload(event:any){
    this.video=event.target.files[0];
  }


}

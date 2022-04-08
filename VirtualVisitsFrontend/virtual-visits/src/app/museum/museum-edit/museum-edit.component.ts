import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Museum } from 'src/app/model/museum.model';
import { MuseumDetails } from 'src/app/model/single-museum.model';
import { GeolocationChooserComponent } from '../geolocation-chooser/geolocation-chooser.component';
import { MapContainerComponent } from '../map-container/map-container.component';
import { MuseumService } from '../services/museum.service';

@Component({
  selector: 'app-museum-edit',
  templateUrl: './museum-edit.component.html',
  styleUrls: ['./museum-edit.component.css']
})
export class MuseumEditComponent implements OnInit {

  public form: FormGroup = new FormGroup({});
  public museum: MuseumDetails = new MuseumDetails();
  public isEdit = false; 
  public countries :any[]=[];
  public cities:any[]=[];
  public longitude:number =90;
  public latitude:number =-90;

  constructor(public formBuilder: FormBuilder, private museumService: MuseumService, private snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<MuseumEditComponent>,
    @Inject(MAT_DIALOG_DATA) data: any, private dialog: MatDialog, private router:Router) { 
    this.isEdit = data.isEdit;
    if(data.isEdit){
      this.museum = data.museum;
    }
   
    this.loadCountries();
    if(this.isEdit && this.museum.country && this.museum.longitude && this.museum.latitude){
      //load cities for country of museum
      this.loadCheckboxes(this.museum.country);
      this.longitude=this.museum.longitude;
      this.latitude=this.museum.latitude;
    }   
  }

  ngOnInit(): void {
      this.form = this.formBuilder.group({
      name: [this.museum.name, Validators.required],
      address: [this.museum.address, Validators.required],
      phone: [this.museum.phone],
      type: [this.museum.type, Validators.required],
      country: [this.museum.country, Validators.required],
      city:[this.museum.city, Validators.required]
    });
  }

  
  loadCountries(){
    this.museumService.getCountries().subscribe((result:any)=>{
      this.countries=result;
    })
  }


  close() {
    this.dialogRef.close();
  }

  save({ value, valid }: { value: MuseumDetails, valid: boolean }){
    if(valid){
      value.longitude=parseFloat(this.longitude.toPrecision(8));
      value.latitude=parseFloat(this.latitude?.toPrecision(8));
      if(this.isEdit) {
        value.museumId=this.museum.museumId;
        this.museumService.updateMuseum(value).subscribe({
          next:data=>{
            console.log("updated");
            this.snackBar.open("Museum is saved", undefined, {
              duration: 2000,
            });
                  
            this.form.reset(); 
            this.dialogRef.close();
          },
          error:err=>{
            console.log(err);
            this.snackBar.open("Museum can't be saved", undefined, {
              duration: 2000,
            });
            if(err.status==401){
              this.router.navigate(['']);
            }
          }
        })
      } else {
        this.museumService.insertMuseum(value).subscribe({
          next:data=>{
            console.log("inserted");
            this.snackBar.open("Museum is saved", undefined, {
              duration: 2000,
            });
            
            this.form.reset(); 
            this.dialogRef.close();
          },
          error:err=>{
            console.log(err);
            this.snackBar.open("Museum can't be saved", undefined, {
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

  loadCheckboxes(country:string){
    this.museumService.getCCA2ForCountryName(country).subscribe((result:any)=>{
      console.log(result);
      var code=result[0].cca2;
      console.log(code);
      var regions: any[]=[];
      //get all regions
      this.museumService.getRegionsForCountry(code).subscribe((result:any)=>{
        result.forEach((r:any)=>{
          this.museumService.getCitiesForRegion(r).subscribe((result:any)=>{
            this.cities.push(...result);
          })
        });
      })
    });
  }

  countryChanged(event: any){
    this.cities=[];
   /* this.museumService.getCCA2ForCountryName(event.value).subscribe((result:any)=>{
      console.log(result);
      var code=result[0].cca2;
      console.log(code);
      var regions: any[]=[];
      //get all regions
      this.museumService.getRegionsForCountry(code).subscribe((result:any)=>{
        result.forEach((r:any)=>{
          this.museumService.getCitiesForRegion(r).subscribe((result:any)=>{
            this.cities.push(...result);
          })
        });
      })
    })*/
    this.loadCheckboxes(event.value);
    console.log("Promjena:"+event.value); 
 //   this.museumService.getCCA2ForCountryName(event.value).subscribe(res=>console.log(res));
  }

  cityChanged(event: any){
    console.log(event.value);
    this.cities.filter(c=>c.city===event.value).forEach(c=>{
      this.longitude=parseFloat(c.longitude);
      this.latitude=parseFloat(c.latitude);
    })
  }

  setGeolocation(){
    console.log(this.longitude);
    console.log(this.latitude);
    this.dialog.open(GeolocationChooserComponent, {
      width: '550px',
      data: {
        longitude: this.longitude,
        latitude: this.latitude
      }
    })
    .afterClosed()
    .subscribe(result => {
      this.longitude=result.longitude,
      this.latitude=result.latitude
    });
  }
}

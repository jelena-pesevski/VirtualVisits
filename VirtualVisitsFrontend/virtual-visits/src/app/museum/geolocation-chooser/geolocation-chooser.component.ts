import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-geolocation-chooser',
  templateUrl: './geolocation-chooser.component.html',
  styleUrls: ['./geolocation-chooser.component.css']
})
export class GeolocationChooserComponent implements OnInit {

  mapOptions: google.maps.MapOptions;
  marker:any;
  sentLng:number;
  sentLat:number;

  constructor( private dialogRef: MatDialogRef<GeolocationChooserComponent>,  @Inject(MAT_DIALOG_DATA) data: any) {   
    this.sentLng=data.longitude;
    this.sentLat=data.latitude
   
    this.mapOptions = {
      center: { lat: data.latitude, lng: data.longitude},
      zoom : 14
   }
   this.marker = {
      position: { lat: data.latitude, lng: data.longitude },
    };
  }

  ngOnInit(): void {
  }


  saveGeolocation(){
    this.dialogRef.close({
      longitude:this.marker.position.lng,
      latitude:this.marker.position.lat
    });
  }

  close() {
    this.dialogRef.close({
      longitude:this.sentLng,
      latitude:this.sentLat
    });
  }

  click(event:any){
    this.marker = {
      position: { lat: event.latLng.lat(), lng: event.latLng.lng() },
    };
  }
}

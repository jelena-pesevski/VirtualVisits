import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { } from '@angular/google-maps';

@Component({
  selector: 'app-map-container',
  templateUrl: './map-container.component.html',
  styleUrls: ['./map-container.component.css']
})
export class MapContainerComponent implements OnInit {

  mapOptions: google.maps.MapOptions;
  marker:any;

  constructor( private dialogRef: MatDialogRef<MapContainerComponent>,  @Inject(MAT_DIALOG_DATA) data: any) { 
    //here we take latitude and longitude from injected data
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

  close() {
    this.dialogRef.close();
  }
}

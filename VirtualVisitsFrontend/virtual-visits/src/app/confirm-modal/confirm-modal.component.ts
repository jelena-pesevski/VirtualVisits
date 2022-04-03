import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-confirm-modal',
  templateUrl: './confirm-modal.component.html',
  styleUrls: ['./confirm-modal.component.css']
})
export class ConfirmModalComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<ConfirmModalComponent>) { }

  ngOnInit() {
  }

  close() {
    this.dialogRef.close();
  }


  confirm() {
    this.dialogRef.close(true);
  }

}
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TherapyDTO } from '../../model/TherapyDTO';

@Component({
  selector: 'app-therapy-details-dialog',
  templateUrl: './therapy-details-dialog.component.html',
  styleUrls: ['./therapy-details-dialog.component.scss']
})
export class TherapyDetailsDialogComponent implements OnInit {


  constructor(public dialogRef: MatDialogRef<TherapyDetailsDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: TherapyDTO) { }

  ngOnInit(): void {
  }

}

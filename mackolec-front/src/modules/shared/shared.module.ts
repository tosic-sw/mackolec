import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { SnackBarService } from './services/snack-bar.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatSnackBarModule,
    MatTableModule
  ],
  providers: [
    SnackBarService
  ]
})
export class SharedModule { }

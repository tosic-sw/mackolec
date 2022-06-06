import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { SnackBarService } from './services/snack-bar.service';
import { SocketService } from './services/socket.service';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatSnackBarModule,
    MatTableModule
  ],
  providers: [
    SnackBarService,
    SocketService
  ]
})
export class SharedModule { }

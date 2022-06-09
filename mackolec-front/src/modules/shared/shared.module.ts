import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { SnackBarService } from './services/snack-bar.service';
import { SocketService } from './services/socket.service';
import { UtilService } from './services/util.service';
import { DateFormatPipe } from './pipes/date-format.pipe';
import { UpperFirstLetterPipe } from './pipes/upper-first-letter.pipe';
import { HospitalizationPipe } from './pipes/hospitalization.pipe';



@NgModule({
  declarations: [
    DateFormatPipe,
    UpperFirstLetterPipe,
    HospitalizationPipe  
  ],
  imports: [
    CommonModule,
    MatSnackBarModule,
    MatTableModule
  ],
  exports: [
    DateFormatPipe, 
    UpperFirstLetterPipe,
    HospitalizationPipe
  ],
  providers: [
    SnackBarService,
    SocketService,
    UtilService
  ],
})
export class SharedModule { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { SnackBarService } from './services/snack-bar.service';
import { SocketService } from './services/socket.service';
import { UtilService } from './services/util.service';
import { DateFormatPipe } from './pipes/date-format.pipe';



@NgModule({
  declarations: [
    DateFormatPipe
  ],
  imports: [
    CommonModule,
    MatSnackBarModule,
    MatTableModule
  ],
  exports: [
    DateFormatPipe
  ],
  providers: [
    SnackBarService,
    SocketService,
    UtilService
  ],
})
export class SharedModule { }

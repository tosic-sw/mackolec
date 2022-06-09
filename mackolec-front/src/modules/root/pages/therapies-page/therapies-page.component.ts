import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { SnackBarService } from 'src/modules/shared/services/snack-bar.service';
import { PaginationComponent } from '../../components/pagination/pagination.component';
import { TherapyDetailsDialogComponent } from '../../components/therapy-details-dialog/therapy-details-dialog.component';
import { TherapyDTO } from '../../model/TherapyDTO';
import { HospitalizedCatService } from '../../services/hospitalized-cat.service';
import { TherapyService } from '../../services/therapy.service';

@Component({
  selector: 'app-therapies-page',
  templateUrl: './therapies-page.component.html',
  styleUrls: ['./therapies-page.component.scss']
})
export class TherapiesPageComponent implements OnInit {

  therapies: TherapyDTO[];
  pageSize: number;
  currentPage: number;
  totalSize: number;
  searchText: string;

  @ViewChild(PaginationComponent) pagination!: PaginationComponent;

  constructor(private therapyService: TherapyService, private snackBarService: SnackBarService,
              public dialog: MatDialog) { 
    this.therapies = [];
    this.pageSize = 10;
    this.currentPage = 1;
    this.totalSize = 1;
    this.searchText = "";
  }

  ngOnInit(): void {
    this.changePage(this.currentPage);
  }

  changePage(newPage: any) {
    let newPageNumber = newPage as number;

    this.therapyService.search(this.searchText, newPageNumber - 1, this.pageSize).subscribe((response: any) => {
      this.therapies = response.body;
      this.totalSize = Number(response.headers.get("total-elements"));

      if(newPage === 1)
        this.pagination.reset();
    },
    (error) => {
      if(error.status === 500)
        this.snackBarService.openSnackBar("An unknown error ocured while loading therapies");
    });
  }

  search(event: any) {
    let text = event as string;
    if(!text) text = "";

    this.searchText = text;

    this.changePage(1);
  }

  details(therapy: TherapyDTO): void {
    console.log(therapy);

    let dialogRef = this.dialog.open(TherapyDetailsDialogComponent, {
      width: '70%',
      data: therapy
    });
  
    dialogRef.afterClosed().subscribe(result => {
      
    });

  }

}

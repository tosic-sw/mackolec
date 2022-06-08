import { Component, OnInit, ViewChild } from '@angular/core';
import { HospitalizedCatDTO } from 'src/modules/shared/models/HospitalizedCatDTO';
import { SnackBarService } from 'src/modules/shared/services/snack-bar.service';
import { PaginationComponent } from '../../components/pagination/pagination.component';
import { HospitalizedCatService } from '../../services/hospitalized-cat.service';

@Component({
  selector: 'app-hospitalized-cats-page',
  templateUrl: './hospitalized-cats-page.component.html',
  styleUrls: ['./hospitalized-cats-page.component.scss']
})
export class HospitalizedCatsPageComponent implements OnInit {

  hcats: HospitalizedCatDTO[];
  pageSize: number;
  currentPage: number;
  totalSize: number;
  searchText: string;

  @ViewChild(PaginationComponent) pagination!: PaginationComponent;

  constructor(private hospitalizedCatService: HospitalizedCatService, private snackBarService: SnackBarService) {
    this.hcats = [];
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

    this.hospitalizedCatService.search(this.searchText, newPageNumber - 1, this.pageSize).subscribe((response: any) => {
      this.hcats = response.body;
      this.totalSize = Number(response.headers.get("total-elements"));

      if(newPage === 1)
        this.pagination.reset();
    },
    (error) => {
      if(error.status === 500)
        this.snackBarService.openSnackBar("An unknown error ocured while loading notifications");
    });
  }

  search(event: any) {
    let text = event as string;
    if(!text) text = "";

    this.searchText = text;

    this.changePage(1);
  }

}

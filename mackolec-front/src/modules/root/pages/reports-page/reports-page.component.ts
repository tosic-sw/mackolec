import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { SnackBarService } from 'src/modules/shared/services/snack-bar.service';
import { PaginationComponent } from '../../components/pagination/pagination.component';
import { ReportBreed, ReportBreedDTO } from '../../model/ReportBreedDTO';
import { ReportDTO } from '../../model/ReportDTO';
import { ReportsService } from '../../services/reports.service';

@Component({
  selector: 'app-reports-page',
  templateUrl: './reports-page.component.html',
  styleUrls: ['./reports-page.component.scss']
})
export class ReportsPageComponent implements AfterViewInit {

  reportContent: ReportDTO[];
  reportsType: string[] = ["Aquired immunity", "Risk of organ damage", "Possible chronic disease", "Therapies per cat breed"]
  selectedReportType: string = this.reportsType[0];

  medicineCategories = ['SREDNJE_JAK_LEK', 'JAK_LEK'];
  selectedMedicineCategory: string = this.medicineCategories[0];

  possibleTitles: string[] = ["Cats that have acquired immunity to a particular drug they have used", "Cats at risk of organ damage", "Cats with possible chronic diseases"]
  tableTitle: string = this.possibleTitles[0];

  showTable = true;


  showPie = false;
  dataPie: ReportBreed[];
  pieTitle = "The breeds of cats that get sick most often"

  @ViewChild(PaginationComponent) pagination!: PaginationComponent;

  constructor(private reportService: ReportsService, private snackBarService: SnackBarService) {
    this.reportContent = [];
    this.dataPie = [];
  }

  ngAfterViewInit(): void {
    this.getReport();
    this.getReportBreed();
  }

  getReport() {
    let index = this.reportsType.indexOf(this.selectedReportType);
    if (index >= 0 && index <= 2)
      this.reportService.getReport(this.selectedReportType, this.selectedMedicineCategory).subscribe((response: any) => {
        let index = this.reportsType.indexOf(this.selectedReportType);
        this.tableTitle = this.possibleTitles[index];
        this.reportContent = response.body;
        this.showTable = true;
        this.showPie = false;
      },
        (error) => {
          if (error.status === 500)
            this.snackBarService.openSnackBar("An unknown error ocured while loading report");
        });
    else {
      this.showTable = false;
      this.showPie = true;
    }

  }

  getReportBreed() {
    this.reportService.getReportCatBreed().subscribe((response: any) => {
      let reportBreed: ReportBreedDTO = response.body;
      let numOfTherapies = 0;

      reportBreed.breeds.forEach(element => {
        numOfTherapies += element.numOfOccurance;
      });

      reportBreed.breeds.forEach(reportBreed => {
        if (reportBreed.numOfOccurance != 0) {
          let avgOccurance = Math.round(100 * reportBreed.numOfOccurance / numOfTherapies);
          this.dataPie.push({ breedName: reportBreed.breedName, numOfOccurance: avgOccurance });
        }
      });
      this.sortBreedReport();
    },
      (error) => {
        if (error.status === 500)
          this.snackBarService.openSnackBar("An unknown error ocured while loading breed report");
      });
  }

  sortBreedReport() {
    this.dataPie = this.dataPie.sort((n1, n2) => {
      if (n1.numOfOccurance > n2.numOfOccurance) {
        return -1;
      }

      if (n1.numOfOccurance < n2.numOfOccurance) {
        return 1;
      }

      return 0;
    });
  }

}

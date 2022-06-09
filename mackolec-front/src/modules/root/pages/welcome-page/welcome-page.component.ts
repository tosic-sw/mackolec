import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SnackBarService } from 'src/modules/shared/services/snack-bar.service';
import { CatInfoDTO } from '../../model/CatInfoDTO';
import { CatObservationDTO } from '../../model/CatObservationDTO';
import { SymptomDTO } from '../../model/SymptomDTO';
import { ObservationService } from '../../services/observation.service';
import { WelcomeService } from '../../services/welcome.service';

@Component({
  selector: 'app-welcome-page',
  templateUrl: './welcome-page.component.html',
  styleUrls: ['./welcome-page.component.scss']
})
export class WelcomePageComponent {

  currentTab = 0;

  symptoms: SymptomDTO[];

  constructor(
    private welcomeService: WelcomeService,
    private snackBarService: SnackBarService,
    private observationService: ObservationService,
    private router: Router) {
    this.symptoms = [];
  }

  gretting() {
    this.welcomeService.grettingFromServer()
      .subscribe((response: any) => {
        this.snackBarService.openSnackBar(response.body as string);
      },
        (error) => {
          this.snackBarService.openSnackBar(error.error as string);
        });
  }

  catInfo(catInfoDTO: CatInfoDTO) {
    console.log(catInfoDTO);
    if (this.symptoms.length == 0) {
      this.snackBarService.openSnackBar("U must select at least one symptom and confirm it.");
      return;
    }
    let catObservationDTO: CatObservationDTO = {
      catInfo: catInfoDTO,
      observedSymptoms: this.symptoms
    }
    this.observationService.saveObservation(catObservationDTO).subscribe((response: any) => {
      this.snackBarService.openSnackBar("Cat observed and therapy is determined.");
      console.log(response.body);
      // window.location.reload();
    },
      (err: any) => {
        this.snackBarService.openSnackBar(err.error);
      }
    );
  }

  symptomsDTO(symptoms: SymptomDTO[]) {
    this.currentTab = 1;
    console.log(symptoms);
    this.symptoms = symptoms;
  }
}

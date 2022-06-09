import { AfterViewInit, Component, EventEmitter, OnInit, Output } from '@angular/core';
import { SnackBarService } from 'src/modules/shared/services/snack-bar.service';
import { SymptomDTO } from '../../model/SymptomDTO';
import { SymptomService } from '../../services/symptom.service';

@Component({
  selector: 'app-symptoms',
  templateUrl: './symptoms.component.html',
  styleUrls: ['./symptoms.component.scss']
})
export class SymptomsComponent implements AfterViewInit {

  symptoms: SymptomDTO[];

  @Output() symptomsEvent = new EventEmitter<SymptomDTO[]>();

  constructor(
    private symptomService: SymptomService,
    private snackBarService: SnackBarService) {
    this.symptoms = [];
  }

  ngAfterViewInit(): void {
    this.symptomService.getSymptoms().subscribe(
      (response) => {
        this.symptoms = response.body as SymptomDTO[];
      },
      (error) => {
        this.snackBarService.openSnackBar(error.error);
      })
  }

  updateIntensity(event: any, symptom: SymptomDTO) {
    let index = this.symptoms.indexOf(symptom);
    this.symptoms[index].intensity = event.value;
  }

  sendSymptoms() {
    let observedSymptoms: SymptomDTO[] = [];
    this.symptoms.forEach(symptom => {
      if (symptom.hasOwnProperty('intensity') && symptom.intensity != 0)
        observedSymptoms.push(symptom);
    })
    this.symptomsEvent.emit(observedSymptoms);
  }
}

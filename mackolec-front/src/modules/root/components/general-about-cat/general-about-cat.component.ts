import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { EmailValidator, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SnackBarService } from 'src/modules/shared/services/snack-bar.service';
import { CatInfoDTO } from '../../model/CatInfoDTO';
import { Breed } from '../../model/enum/Breed';
import { Gender } from '../../model/enum/Gender';
import { ObservationService } from '../../services/observation.service';
import { BasicValidator } from '../../validators/BasicValidator';
import { IntValidator } from '../../validators/IntValidator';
import { MaxLengthValidator } from '../../validators/MaxLengthValidator';
import { MinLengthValidator } from '../../validators/MinLengthValidator';

@Component({
  selector: 'app-general-about-cat',
  templateUrl: './general-about-cat.component.html',
  styleUrls: ['./general-about-cat.component.scss']
})
export class GeneralAboutCatComponent implements OnInit {

  form: FormGroup;
  breeds: string[] = [
    Breed.RUSKA.toString(),
    Breed.MAINE_COON.toString(),
    Breed.SIBIRSKA.toString(),
    Breed.SIJAMSKA.toString(),
    Breed.SKOTSKA.toString(),
    Breed.SFINKS.toString()];
  genders: string[] = [
    Gender.MALE.toString(),
    Gender.FEMALE.toString()];

  @Output() catInfoEvent = new EventEmitter<CatInfoDTO>();

  constructor(
    private fb: FormBuilder,
    private snackBarService: SnackBarService,
    private observationService: ObservationService) {
    this.form = this.fb.group({
      jmbm: [null, [Validators.required, BasicValidator, MinLengthValidator, MaxLengthValidator]],
      name: [null, [Validators.required, BasicValidator, MinLengthValidator, MaxLengthValidator]],
      age: [null, [Validators.required, IntValidator]],
      weight: [null, [Validators.required, IntValidator]],
      breed: [null, [Validators.required]],
      gender: [null, [Validators.required]],
    })
  }

  get f() {
    return this.form.controls;
  }

  ngOnInit(): void {
  }

  submit() {
    const catInfo: CatInfoDTO = {
      jmbm: this.form.value.jmbm,
      name: this.form.value.name,
      age: this.form.value.age,
      weight: this.form.value.weight,
      breed: this.form.value.breed,
      gender: this.form.value.gender
    };

    this.catInfoEvent.emit(catInfo);
  }

}

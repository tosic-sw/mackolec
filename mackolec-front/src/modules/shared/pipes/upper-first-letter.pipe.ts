import { Pipe, PipeTransform } from '@angular/core';
import * as moment from 'moment';

@Pipe({
  name: 'upperFirstLetter'
})
export class UpperFirstLetterPipe implements PipeTransform {

  transform(value: string): any {
    if (value == "MAINE_COON")
      value = "MAINE COON"
    value = value.toLowerCase();
    return value.charAt(0).toUpperCase() + value.slice(1);
  }

}
import { Pipe, PipeTransform } from '@angular/core';
import * as moment from 'moment';

@Pipe({
  name: 'hospitalization'
})
export class HospitalizationPipe implements PipeTransform {

  transform(value: string): any {
    if(value === 'NEED') return "Needed";
    if(value === 'NO_NEED') return "Not needed";
    return "";
  }

}
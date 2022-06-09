import { AbstractControl } from '@angular/forms';

export function BasicValidator(control: AbstractControl) {
  if (!control.value) return null;

  const isValid = /^[a-zA-Z0-9]*$/.test(control.value);

  if (!isValid)
    return { invalidString: true };

  return null;
}
import { AbstractControl } from '@angular/forms';

export function IntValidator(control: AbstractControl) {
    if (!control.value) return null;

    const isValid = /^[1-9]\d*$/.test(control.value);

    if (!isValid)
        return { invalidNumber: true };

    return null;
}
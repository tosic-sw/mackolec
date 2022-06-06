import { Component } from '@angular/core';
import { SnackBarService } from 'src/modules/shared/services/snack-bar.service';
import { WelcomeService } from '../../services/welcome.service';

@Component({
  selector: 'app-welcome-page',
  templateUrl: './welcome-page.component.html',
  styleUrls: ['./welcome-page.component.scss']
})
export class WelcomePageComponent {

  constructor(private welcomeService: WelcomeService, private snackBarService: SnackBarService) { }

  gretting() {
    this.welcomeService.grettingFromServer()
      .subscribe((response: any) => {
        this.snackBarService.openSnackBar(response.body as string);
      },
        (error) => {
          this.snackBarService.openSnackBar(error.error as string);
        });
  }
}

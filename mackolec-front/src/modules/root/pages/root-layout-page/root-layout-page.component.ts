import { Component } from '@angular/core';
import { SocketService } from 'src/modules/shared/services/socket.service';

@Component({
  selector: 'app-root-layout-page',
  templateUrl: './root-layout-page.component.html',
  styleUrls: ['./root-layout-page.component.scss']
})
export class RootLayoutPageComponent {

  constructor(private webSocketService: SocketService) {}

  connectSocket(): void {
    this.webSocketService.connect();
  }

}

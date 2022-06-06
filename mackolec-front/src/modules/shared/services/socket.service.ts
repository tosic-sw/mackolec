import { Injectable } from '@angular/core';

import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

import { SnackBarService } from './snack-bar.service';
import { AlarmNotification } from '../models/AlarmNotification';


@Injectable({
  providedIn: 'root'
})
export class SocketService {

  url: string = '/socket';

  private stompClient: any;
  initialized: boolean = false;

  constructor(private snackBarService: SnackBarService) { }

  connect(): void {
    if (this.initialized) {
      return;
    }
    this.initialized = true;

    const socket = new SockJS("http://localhost:8080/socket/");
    this.stompClient = Stomp.over(socket);

    const that = this;
    this.stompClient.connect({}, function (frame: string) {
      that.initialized = true;
      console.log('Connected: ' + frame);

      that.stompClient.subscribe('/socket-publisher', function (message: { body: string }) {
        // Showing message
        let notification = JSON.parse(message.body) as AlarmNotification;
        console.log(notification);
        that.snackBarService.openSnackBar("Ciao");
      });

    });

    console.log(this.stompClient)
  }

  disconnect(): void {
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }

    this.initialized = false;
    console.log('Disconnected!');
  }

  sendMessage(message: any): void {
    this.stompClient.send('/socket-subscriber/send/message', {}, JSON.stringify(message));
  }

}

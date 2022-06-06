import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { AlarmNotification } from '../models/AlarmNotification';


@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private httpClient: HttpClient) { }

  findAll(username: string, page: number, size: number): Observable<HttpResponse<AlarmNotification[]>> {
    let queryParams = {};

    queryParams = {
      headers: new HttpHeaders({ "Content-Type": 'application/json' }),
      observe: 'response',
      params: new HttpParams()
        .set("page", String(page))
        .append("size", String(size))
        .append("sort", "id,desc")
    };

    return this.httpClient.get<HttpResponse<AlarmNotification[]>>(`mackolec/api/alarmNotifications`, queryParams);
  }

}
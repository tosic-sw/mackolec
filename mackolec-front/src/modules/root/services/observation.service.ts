import { HttpHeaders, HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CatObservationDTO } from '../model/CatObservationDTO';

@Injectable({
  providedIn: 'root'
})
export class ObservationService {

  private headers = new HttpHeaders({ "Content-Type": "application/json" });

  constructor(private http: HttpClient) { }

  saveObservation(catObservation: CatObservationDTO): Observable<HttpResponse<any>> {
    let queryParams = {};

    queryParams = {
      headers: this.headers,
      observe: "response",
      responseType: "json"
    };

    return this.http.post<HttpResponse<any>>("mackolec/api/observation", catObservation, queryParams);
  }
}

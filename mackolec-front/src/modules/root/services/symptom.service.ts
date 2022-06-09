import { HttpHeaders, HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SymptomDTO } from '../model/SymptomDTO';

@Injectable({
  providedIn: 'root'
})
export class SymptomService {

  private headers = new HttpHeaders({ "Content-Type": "application/json" });

  constructor(private http: HttpClient) { }

  getSymptoms(): Observable<HttpResponse<SymptomDTO[]>> {
    let queryParams = {};

    queryParams = {
      headers: this.headers,
      observe: "response"
    };

    return this.http.get<HttpResponse<SymptomDTO[]>>("mackolec/api/symptoms", queryParams);
  }
}

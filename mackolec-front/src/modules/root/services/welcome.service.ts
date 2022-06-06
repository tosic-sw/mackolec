import { HttpHeaders, HttpClient, HttpResponse, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WelcomeService {

  private headers = new HttpHeaders({ "Content-Type": "application/json" });

  constructor(private http: HttpClient) { }

  grettingFromServer(): Observable<HttpResponse<string>> {
    let queryParams = {};

    queryParams = {
      headers: this.headers,
      observe: "response",
      responseType: "text"
    };
    return this.http.get<HttpResponse<string>>("mackolec/api/test", queryParams);
  }
}

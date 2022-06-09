import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { HospitalizedCatDTO } from 'src/modules/shared/models/HospitalizedCatDTO';
import { TherapyDTO } from '../model/TherapyDTO';

@Injectable({
  providedIn: 'root'
})
export class TherapyService {

  constructor(private httpClient: HttpClient) { }

  search(search: string, page: number, size: number): Observable<HttpResponse<TherapyDTO[]>> {
    let queryParams = {};

    queryParams = {
      headers: new HttpHeaders({ "Content-Type": 'application/json' }),
      observe: 'response',
      params: new HttpParams()
        .set("search", search)
        .append("page", String(page))
        .append("size", String(size))
        .append("sort", "id,asc")
    };

    return this.httpClient.get<HttpResponse<TherapyDTO[]>>(`mackolec/api/therapy`, queryParams);
  }

}
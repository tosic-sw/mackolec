import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { HospitalizedCatDTO } from 'src/modules/shared/models/HospitalizedCatDTO';

@Injectable({
  providedIn: 'root'
})
export class HospitalizedCatService {

  constructor(private httpClient: HttpClient) { }

  search(search: string, page: number, size: number): Observable<HttpResponse<HospitalizedCatDTO[]>> {
    let queryParams = {};

    queryParams = {
      headers: new HttpHeaders({ "Content-Type": 'application/json' }),
      observe: 'response',
      params: new HttpParams()
        .set("search", search)
        .append("page", String(page))
        .append("size", String(size))
        .append("sort", "id,desc")  
    };

    return this.httpClient.get<HttpResponse<HospitalizedCatDTO[]>>(`mackolec/api/hospitalizedCat`, queryParams);
  }

}
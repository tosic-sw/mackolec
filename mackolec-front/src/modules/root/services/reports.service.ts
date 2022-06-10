import { HttpHeaders, HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReportBreedDTO } from '../model/ReportBreedDTO';
import { ReportDTO } from '../model/ReportDTO';

@Injectable({
  providedIn: 'root'
})
export class ReportsService {

  private headers = new HttpHeaders({ "Content-Type": "application/json" });

  constructor(private http: HttpClient) { }

  getReport(selectedType: string, medicineCategory: string): Observable<HttpResponse<ReportDTO>> {
    let queryParams = {};

    queryParams = {
      headers: this.headers,
      observe: "response",
      responseType: "json"
    };

    selectedType = this.checkReportType(selectedType);
    if (selectedType == "riskOfOrganDamage")
      return this.http.get<HttpResponse<ReportDTO>>("mackolec/api/reports/" + selectedType + "/" + medicineCategory, queryParams);

    return this.http.get<HttpResponse<ReportDTO>>("mackolec/api/reports/" + selectedType, queryParams);
  }

  getReportCatBreed(): Observable<HttpResponse<ReportBreedDTO>> {
    let queryParams = {};

    queryParams = {
      headers: this.headers,
      observe: "response",
      responseType: "json"
    };

    return this.http.get<HttpResponse<ReportBreedDTO>>("mackolec/api/reports/catBreed", queryParams);
  }

  checkReportType(reportType: string) {
    let reportsType: string[] = ["Aquired immunity", "Risk of organ damage", "Possible chronic disease", "Therapies per cat breed"]
    let index = reportsType.indexOf(reportType);
    if (index == 0)
      return "aquiredImmunity";
    if (index == 1)
      return "riskOfOrganDamage";
    if (index == 2)
      return "possibleChronicDisease";
    return "none";
  }
}

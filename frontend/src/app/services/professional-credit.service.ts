import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api/api.service';
import { ProfessionalCredit } from '../models/professional-credit.model';

@Injectable({
  providedIn: 'root'
})
export class ProfessionalCreditService {
  private endpoint = 'professional-credits';

  constructor(private apiService: ApiService) { }

  getAllProfessionalCredits(): Observable<ProfessionalCredit[]> {
    return this.apiService.get<ProfessionalCredit[]>(this.endpoint);
  }

  getProfessionalCreditById(id: number): Observable<ProfessionalCredit> {
    return this.apiService.getById<ProfessionalCredit>(this.endpoint, id);
  }

  createProfessionalCredit(credit: ProfessionalCredit): Observable<ProfessionalCredit> {
    return this.apiService.post<ProfessionalCredit>(this.endpoint, credit);
  }

  updateProfessionalCredit(id: number, credit: ProfessionalCredit): Observable<ProfessionalCredit> {
    return this.apiService.put<ProfessionalCredit>(this.endpoint, id, credit);
  }

  deleteProfessionalCredit(id: number): Observable<void> {
    return this.apiService.delete(this.endpoint, id);
  }
}

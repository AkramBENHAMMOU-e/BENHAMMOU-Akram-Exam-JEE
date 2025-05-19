import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api/api.service';
import { PersonalCredit } from '../models/personal-credit.model';

@Injectable({
  providedIn: 'root'
})
export class PersonalCreditService {
  private endpoint = 'personal-credits';

  constructor(private apiService: ApiService) { }

  getAllPersonalCredits(): Observable<PersonalCredit[]> {
    return this.apiService.get<PersonalCredit[]>(this.endpoint);
  }

  getPersonalCreditById(id: number): Observable<PersonalCredit> {
    return this.apiService.getById<PersonalCredit>(this.endpoint, id);
  }

  createPersonalCredit(credit: PersonalCredit): Observable<PersonalCredit> {
    return this.apiService.post<PersonalCredit>(this.endpoint, credit);
  }

  updatePersonalCredit(id: number, credit: PersonalCredit): Observable<PersonalCredit> {
    return this.apiService.put<PersonalCredit>(this.endpoint, id, credit);
  }

  deletePersonalCredit(id: number): Observable<void> {
    return this.apiService.delete(this.endpoint, id);
  }
}

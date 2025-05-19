import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api/api.service';
import { Credit } from '../models/credit.model';
import { CreditStatus } from '../models/enums/credit-status.enum';

@Injectable({
  providedIn: 'root'
})
export class CreditService {
  private endpoint = 'credits';

  constructor(private apiService: ApiService) { }

  getCreditsByStatus(status: CreditStatus): Observable<Credit[]> {
    return this.apiService.get<Credit[]>(`${this.endpoint}/status/${status}`);
  }

  changeCreditStatus(id: number, status: CreditStatus): Observable<void> {
    return this.apiService.put<void>(`${this.endpoint}/${id}/status`, id, { status });
  }
}

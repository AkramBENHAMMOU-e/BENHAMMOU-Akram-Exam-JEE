import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api/api.service';
import { Repayment } from '../models/repayment.model';

@Injectable({
  providedIn: 'root'
})
export class RepaymentService {
  private endpoint = 'repayments';

  constructor(private apiService: ApiService) { }

  getRepaymentById(id: number): Observable<Repayment> {
    return this.apiService.getById<Repayment>(this.endpoint, id);
  }

  getRepaymentsByCreditId(creditId: number): Observable<Repayment[]> {
    return this.apiService.get<Repayment[]>(`${this.endpoint}/credit/${creditId}`);
  }

  createRepayment(repayment: Repayment): Observable<Repayment> {
    return this.apiService.post<Repayment>(this.endpoint, repayment);
  }

  updateRepayment(id: number, repayment: Repayment): Observable<Repayment> {
    return this.apiService.put<Repayment>(this.endpoint, id, repayment);
  }

  deleteRepayment(id: number): Observable<void> {
    return this.apiService.delete(this.endpoint, id);
  }
}

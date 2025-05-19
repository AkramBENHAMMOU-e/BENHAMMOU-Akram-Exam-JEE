import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api/api.service';
import { RealEstateCredit } from '../models/real-estate-credit.model';

@Injectable({
  providedIn: 'root'
})
export class RealEstateCreditService {
  private endpoint = 'real-estate-credits';

  constructor(private apiService: ApiService) { }

  getAllRealEstateCredits(): Observable<RealEstateCredit[]> {
    return this.apiService.get<RealEstateCredit[]>(this.endpoint);
  }

  getRealEstateCreditById(id: number): Observable<RealEstateCredit> {
    return this.apiService.getById<RealEstateCredit>(this.endpoint, id);
  }

  createRealEstateCredit(credit: RealEstateCredit): Observable<RealEstateCredit> {
    return this.apiService.post<RealEstateCredit>(this.endpoint, credit);
  }

  updateRealEstateCredit(id: number, credit: RealEstateCredit): Observable<RealEstateCredit> {
    return this.apiService.put<RealEstateCredit>(this.endpoint, id, credit);
  }

  deleteRealEstateCredit(id: number): Observable<void> {
    return this.apiService.delete(this.endpoint, id);
  }
}

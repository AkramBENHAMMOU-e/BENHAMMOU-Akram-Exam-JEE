import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api/api.service';
import { Client } from '../models/client.model';
import { Credit } from '../models/credit.model';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private endpoint = 'clients';

  constructor(private apiService: ApiService) { }

  getAllClients(): Observable<Client[]> {
    return this.apiService.get<Client[]>(this.endpoint);
  }

  getClientById(id: number): Observable<Client> {
    return this.apiService.getById<Client>(this.endpoint, id);
  }

  getClientCredits(id: number): Observable<Credit[]> {
    return this.apiService.get<Credit[]>(`${this.endpoint}/${id}/credits`);
  }

  createClient(client: Client): Observable<Client> {
    return this.apiService.post<Client>(this.endpoint, client);
  }

  updateClient(id: number, client: Client): Observable<Client> {
    return this.apiService.put<Client>(this.endpoint, id, client);
  }

  deleteClient(id: number): Observable<void> {
    return this.apiService.delete(this.endpoint, id);
  }
}

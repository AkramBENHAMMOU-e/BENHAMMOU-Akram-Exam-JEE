import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { PersonalCreditService } from '../../../services/personal-credit.service';
import { CreditService } from '../../../services/credit.service';
import { PersonalCredit } from '../../../models/personal-credit.model';
import { CreditStatus } from '../../../models/enums/credit-status.enum';

@Component({
  selector: 'app-personal-credit-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './personal-credit-list.component.html',
  styleUrl: './personal-credit-list.component.css'
})
export class PersonalCreditListComponent implements OnInit {
  personalCredits: PersonalCredit[] = [];
  loading = false;
  error: string | null = null;
  CreditStatus = CreditStatus; // Make enum available in template

  constructor(
    private personalCreditService: PersonalCreditService,
    private creditService: CreditService
  ) {}

  ngOnInit(): void {
    this.loadPersonalCredits();
  }

  loadPersonalCredits(): void {
    this.loading = true;
    this.error = null;

    this.personalCreditService.getAllPersonalCredits().subscribe({
      next: (data) => {
        this.personalCredits = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading personal credits', err);
        this.error = 'Failed to load personal credits. Please try again later.';
        this.loading = false;
      }
    });
  }

  deletePersonalCredit(id: number): void {
    if (confirm('Are you sure you want to delete this personal credit?')) {
      this.personalCreditService.deletePersonalCredit(id).subscribe({
        next: () => {
          this.personalCredits = this.personalCredits.filter(credit => credit.id !== id);
        },
        error: (err) => {
          console.error('Error deleting personal credit', err);
          alert('Failed to delete personal credit. Please try again later.');
        }
      });
    }
  }

  changeStatus(id: number, status: CreditStatus): void {
    this.creditService.changeCreditStatus(id, status).subscribe({
      next: () => {
        const credit = this.personalCredits.find(c => c.id === id);
        if (credit) {
          credit.status = status;
          if (status === CreditStatus.ACCEPTED) {
            credit.acceptanceDate = new Date();
          }
        }
      },
      error: (err) => {
        console.error('Error changing credit status', err);
        alert('Failed to change credit status. Please try again later.');
      }
    });
  }
}

import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ProfessionalCredit } from '../../../models/professional-credit.model';
import { CreditService } from '../../../services/credit.service';
import { ProfessionalCreditService } from '../../../services/professional-credit.service';
import { CreditStatus } from '../../../models/enums/credit-status.enum';

@Component({
  selector: 'app-professional-credit-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './professional-credit-list.component.html',
  styleUrls: ['./professional-credit-list.component.css']
})
export class ProfessionalCreditListComponent implements OnInit {
  professionalCredits: ProfessionalCredit[] = [];
  loading = true;
  error: string | null = null;
  CreditStatus = CreditStatus; // Make enum available in template

  constructor(
    private professionalCreditService: ProfessionalCreditService,
    private creditService: CreditService
  ) {}

  ngOnInit(): void {
    this.loadProfessionalCredits();
  }

  loadProfessionalCredits(): void {
    this.loading = true;
    this.error = null;

    this.professionalCreditService.getAllProfessionalCredits().subscribe({
      next: (credits) => {
        this.professionalCredits = credits;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading professional credits', err);
        this.error = 'Failed to load professional credits. Please try again.';
        this.loading = false;
      }
    });
  }

  deleteProfessionalCredit(id: number): void {
    if (confirm('Are you sure you want to delete this professional credit?')) {
      this.professionalCreditService.deleteProfessionalCredit(id).subscribe({
        next: () => {
          this.professionalCredits = this.professionalCredits.filter(credit => credit.id !== id);
        },
        error: (err) => {
          console.error('Error deleting professional credit', err);
          alert('Failed to delete professional credit. Please try again.');
        }
      });
    }
  }

  changeStatus(creditId: number, newStatus: CreditStatus): void {
    this.creditService.changeCreditStatus(creditId, newStatus).subscribe({
      next: () => {
        // Update the status in the local array
        const credit = this.professionalCredits.find(c => c.id === creditId);
        if (credit) {
          credit.status = newStatus;

          // If status is ACCEPTED, set acceptance date to today
          if (newStatus === CreditStatus.ACCEPTED) {
            credit.acceptanceDate = new Date();
          }
        }
      },
      error: (err) => {
        console.error('Error changing credit status', err);
        alert('Failed to change credit status. Please try again.');
      }
    });
  }

  getStatusBadgeClass(status: CreditStatus): string {
    switch (status) {
      case CreditStatus.ACCEPTED:
        return 'bg-success';
      case CreditStatus.REJECTED:
        return 'bg-danger';
      case CreditStatus.IN_PROGRESS:
      default:
        return 'bg-warning';
    }
  }
}

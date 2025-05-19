import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { RealEstateCreditService } from '../../../services/real-estate-credit.service';
import { CreditService } from '../../../services/credit.service';
import { RealEstateCredit } from '../../../models/real-estate-credit.model';
import { CreditStatus } from '../../../models/enums/credit-status.enum';
import { PropertyType } from '../../../models/enums/property-type.enum';

@Component({
  selector: 'app-real-estate-credit-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './real-estate-credit-list.component.html',
  styleUrl: './real-estate-credit-list.component.css'
})
export class RealEstateCreditListComponent implements OnInit {
  realEstateCredits: RealEstateCredit[] = [];
  loading = false;
  error: string | null = null;
  CreditStatus = CreditStatus; // Make enum available in template
  PropertyType = PropertyType; // Make enum available in template

  constructor(
    private realEstateCreditService: RealEstateCreditService,
    private creditService: CreditService
  ) {}

  ngOnInit(): void {
    this.loadRealEstateCredits();
  }

  loadRealEstateCredits(): void {
    this.loading = true;
    this.error = null;

    this.realEstateCreditService.getAllRealEstateCredits().subscribe({
      next: (data) => {
        this.realEstateCredits = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading real estate credits', err);
        this.error = 'Failed to load real estate credits. Please try again later.';
        this.loading = false;
      }
    });
  }

  deleteRealEstateCredit(id: number): void {
    if (confirm('Are you sure you want to delete this real estate credit?')) {
      this.realEstateCreditService.deleteRealEstateCredit(id).subscribe({
        next: () => {
          this.realEstateCredits = this.realEstateCredits.filter(credit => credit.id !== id);
        },
        error: (err) => {
          console.error('Error deleting real estate credit', err);
          alert('Failed to delete real estate credit. Please try again later.');
        }
      });
    }
  }

  changeStatus(id: number, status: CreditStatus): void {
    this.creditService.changeCreditStatus(id, status).subscribe({
      next: () => {
        const credit = this.realEstateCredits.find(c => c.id === id);
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

  getPropertyTypeLabel(type: PropertyType): string {
    switch (type) {
      case PropertyType.APARTMENT:
        return 'Apartment';
      case PropertyType.HOUSE:
        return 'House';
      case PropertyType.COMMERCIAL_PROPERTY:
        return 'Commercial Property';
      default:
        return type;
    }
  }
}

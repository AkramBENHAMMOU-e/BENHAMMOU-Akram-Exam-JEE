import { Component, OnInit, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { RepaymentService } from '../../services/repayment.service';
import { Repayment } from '../../models/repayment.model';
import { RepaymentType } from '../../models/enums/repayment-type.enum';

@Component({
  selector: 'app-repayment-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './repayment-list.component.html',
  styleUrl: './repayment-list.component.css'
})
export class RepaymentListComponent implements OnInit {
  @Input() creditId?: number;

  repayments: Repayment[] = [];
  loading = false;
  error: string | null = null;
  RepaymentType = RepaymentType; // Make enum available in template

  constructor(private repaymentService: RepaymentService) {}

  ngOnInit(): void {
    this.loadRepayments();
  }

  loadRepayments(): void {
    this.loading = true;
    this.error = null;

    if (this.creditId) {
      // Load repayments for a specific credit
      this.repaymentService.getRepaymentsByCreditId(this.creditId).subscribe({
        next: (data) => {
          this.repayments = data;
          this.loading = false;
        },
        error: (err) => {
          console.error('Error loading repayments', err);
          this.error = 'Failed to load repayments. Please try again later.';
          this.loading = false;
        }
      });
    } else {
      // This would require a new endpoint to get all repayments
      // For now, we'll just set an error
      this.error = 'Please select a credit to view its repayments.';
      this.loading = false;
    }
  }

  deleteRepayment(id: number): void {
    if (confirm('Are you sure you want to delete this repayment?')) {
      this.repaymentService.deleteRepayment(id).subscribe({
        next: () => {
          this.repayments = this.repayments.filter(repayment => repayment.id !== id);
        },
        error: (err) => {
          console.error('Error deleting repayment', err);
          alert('Failed to delete repayment. Please try again later.');
        }
      });
    }
  }

  getRepaymentTypeLabel(type: RepaymentType): string {
    switch (type) {
      case RepaymentType.MONTHLY_PAYMENT:
        return 'Monthly Payment';
      case RepaymentType.EARLY_REPAYMENT:
        return 'Early Repayment';
      default:
        return type;
    }
  }
}

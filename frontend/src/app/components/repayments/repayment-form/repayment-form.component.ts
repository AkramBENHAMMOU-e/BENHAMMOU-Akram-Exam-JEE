import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { RepaymentService } from '../../../services/repayment.service';
import { CreditService } from '../../../services/credit.service';
import { Repayment } from '../../../models/repayment.model';
import { Credit } from '../../../models/credit.model';
import { RepaymentType } from '../../../models/enums/repayment-type.enum';
import { CreditStatus } from '../../../models/enums/credit-status.enum';

@Component({
  selector: 'app-repayment-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './repayment-form.component.html',
  styleUrl: './repayment-form.component.css'
})
export class RepaymentFormComponent implements OnInit {
  repaymentForm!: FormGroup;
  isEditMode = false;
  repaymentId?: number;
  creditId?: number;
  loading = false;
  error: string | null = null;
  submitted = false;
  credits: Credit[] = [];
  loadingCredits = false;
  RepaymentType = RepaymentType; // Make enum available in template

  constructor(
    private fb: FormBuilder,
    private repaymentService: RepaymentService,
    private creditService: CreditService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.initForm();

    // Check if we're in edit mode or if we have a credit ID from the route
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      const creditId = params.get('creditId');

      if (creditId) {
        this.creditId = +creditId;
        this.repaymentForm.patchValue({ creditId: this.creditId });
      } else {
        this.loadCredits();
      }

      if (id) {
        this.isEditMode = true;
        this.repaymentId = +id;
        this.loadRepayment(this.repaymentId);
      }
    });
  }

  initForm(): void {
    this.repaymentForm = this.fb.group({
      date: [new Date(), [Validators.required]],
      amount: ['', [Validators.required, Validators.min(0.01)]],
      type: [RepaymentType.MONTHLY_PAYMENT, [Validators.required]],
      creditId: ['', [Validators.required]]
    });
  }

  loadCredits(): void {
    this.loadingCredits = true;
    // Get all accepted credits since they are the ones that can have repayments
    this.creditService.getCreditsByStatus(CreditStatus.ACCEPTED).subscribe({
      next: (credits) => {
        this.credits = credits;
        this.loadingCredits = false;
      },
      error: (err) => {
        console.error('Error loading credits', err);
        this.error = 'Failed to load credits. Please try again.';
        this.loadingCredits = false;
      }
    });
  }

  loadRepayment(id: number): void {
    this.loading = true;
    this.repaymentService.getRepaymentById(id).subscribe({
      next: (repayment) => {
        this.repaymentForm.patchValue({
          date: repayment.date,
          amount: repayment.amount,
          type: repayment.type,
          creditId: repayment.creditId
        });
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading repayment', err);
        this.error = 'Failed to load repayment details. Please try again.';
        this.loading = false;
      }
    });
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.repaymentForm.invalid) {
      return;
    }

    this.loading = true;
    const repaymentData: Repayment = {
      ...this.repaymentForm.value
    };

    if (this.isEditMode && this.repaymentId) {
      // Update existing repayment
      repaymentData.id = this.repaymentId;
      this.repaymentService.updateRepayment(this.repaymentId, repaymentData).subscribe({
        next: () => {
          this.navigateBack();
        },
        error: (err) => {
          console.error('Error updating repayment', err);
          this.error = 'Failed to update repayment. Please try again.';
          this.loading = false;
        }
      });
    } else {
      // Create new repayment
      this.repaymentService.createRepayment(repaymentData).subscribe({
        next: () => {
          this.navigateBack();
        },
        error: (err) => {
          console.error('Error creating repayment', err);
          this.error = 'Failed to create repayment. Please try again.';
          this.loading = false;
        }
      });
    }
  }

  navigateBack(): void {
    if (this.creditId) {
      this.router.navigate(['/credits', this.creditId, 'repayments']);
    } else {
      this.router.navigate(['/repayments']);
    }
  }

  // Getter for easy access to form fields
  get f() { return this.repaymentForm.controls; }
}
